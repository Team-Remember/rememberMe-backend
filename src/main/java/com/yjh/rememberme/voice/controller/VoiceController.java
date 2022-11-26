package com.yjh.rememberme.voice.controller;

import com.yjh.rememberme.Member.service.MemberService;
import com.yjh.rememberme.chat.dto.ChatBotDTO;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.voice.dto.PostVoiceDTO;
import com.yjh.rememberme.voice.dto.VoiceDTO;
import com.yjh.rememberme.voice.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/voice")
public class VoiceController {
    private final VoiceService voiceService;
    private final MemberService memberService;
    private ChatBotDTO chatBotData;

    @Autowired
    public VoiceController(VoiceService voiceService, MemberService memberService) {
        this.voiceService = voiceService;
        this.memberService = memberService;
    }

    //음성 봇 API
//    @PostMapping("/{username}")
//    public ResponseEntity<?> postVoiceChatBot(@PathVariable String username , VoiceDTO voiceDTO) throws IOException {
//        System.out.println(voiceDTO);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        Map<String, Object> responseMap = new HashMap<>();
//
//        //호출 로그쌓기
//        voiceService.postVoiceLog(username, voiceDTO.getOpponentNickname());
//
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
//        headers2.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body = voiceService.addBody(voiceDTO);
//
//        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers2);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String url = "https://be06-119-194-163-123.jp.ngrok.io/voice_chat_bot_inference";
//        System.out.println(voiceDTO);
//
//        ResponseEntity<?> resultMap = restTemplate.postForEntity(url, requestEntity, byte[].class);
//        System.out.println("resultMap = " + resultMap);
//
//        if(resultMap.getBody() == null){
//            return ResponseEntity
//                    .badRequest()
//                    .headers(headers)
//                    .body(new ResponseMessage(400, "postVoiceChatBot failed", responseMap));
//        }
//
//        responseMap.put("voice",resultMap);
//
//
//        return ResponseEntity
//                .created(URI.create("/"+username))
//                .headers(headers)
//                .body(new ResponseMessage(201,"postVoiceChatBot succeed",responseMap));
//    }
    //음성채팅 업로드
    @PostMapping("/postvoice/{username}")
    public ResponseEntity<?> postVoice(@PathVariable String username , PostVoiceDTO postVoiceDTO) throws UnsupportedAudioFileException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        Voice voice = null;
        voice = voiceService.postVoice(username, postVoiceDTO);

        responseMap.put("voicePath",voice.getVoicePath());

        return ResponseEntity
                .created(URI.create("/" + username))
                .headers(headers)
                .body(new ResponseMessage(201, "postVoice succeed", responseMap));
    }

    //음성복원
    @GetMapping("/")
    public ResponseEntity<?> getVoice(@RequestParam("username") String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> map = voiceService.putMap(username);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(map, headers);
        String url = "https://be06-119-194-163-123.jp.ngrok.io/voice_chat_bot_inference";

        ResponseEntity<?> resultMap = restTemplate.postForEntity(url, requestEntity, Map.class);

        responseMap.put("result",resultMap);

        return ResponseEntity
                .created(URI.create("/" + username))
                .headers(headers)
                .body(new ResponseMessage(201, "getVoice succeed", responseMap));
    }

    //음성 복원 API
    @PostMapping("/{username}/restore")
    public ResponseEntity<?> restoreVoiceChatBot(@PathVariable String username , VoiceDTO voiceDTO) throws IOException {
        System.out.println(voiceDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int userId = memberService.findUserIdByNickname(voiceDTO);
        int weId = memberService.findOpponentIdByNickname(voiceDTO);

        voiceService.postVoiceLog(username, voiceDTO.getOpponentNickname());

//        int userId = memberRepository.findByNickname(voiceDTO.getUserNickname()).getId();
//        int weId = memberRepository.findByNickname(voiceDTO.getOpponentNickname()).getId();

        //stt 서버 호출 응답은 json
        ResponseEntity<?> resultMap = voiceService.callSTT(voiceDTO,userId, weId);
        System.out.println("resultMap = " + resultMap.getBody());

        //문자 챗봇 호출 응답은 json
        ResponseEntity<?> resultMap2 = voiceService.callChatBot(resultMap.getBody(), userId, weId);
        System.out.println("resultMap2 = " + resultMap2.getBody());

        //tts 호출 응답은 오디오 파일 XR에 json형태로 보냄 (body에 오디오파일이 바이츠?로 들어있음)
        ResponseEntity<?> resultMap3 = voiceService.callTTS(resultMap2.getBody(), userId, weId);
        System.out.println("resultMap3 = " + resultMap3);


        if(resultMap3.getBody() == null){
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "postVoiceChatBot failed", responseMap));
        }

        responseMap.put("voice",resultMap3);

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"postVoiceChatBot succeed",responseMap));
    }
}


