package com.yjh.rememberme.voice.controller;

import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.voice.dto.VoiceDTO;
import com.yjh.rememberme.voice.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voice")
public class VoiceController {
    private final VoiceService voiceService;

    @Autowired
    public VoiceController(VoiceService voiceService) {
        this.voiceService = voiceService;
    }
    //
    @PostMapping("/{username}")
    public ResponseEntity<?> postVoiceChatBot(@PathVariable String username , VoiceDTO voiceDTO) throws IOException {
        System.out.println(voiceDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

//        voiceService.postVoiceLog(username, voiceDTO.getWeId());
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        headers2.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers2.setContentType(MediaType.MULTIPART_FORM_DATA);
//        voiceDTO.getVoice().getBytes();

//        byte[] voiceData = voiceDTO.getVoice().getBytes();
//        System.out.println("voiceData = " + voiceData);
//        ByteArrayResource voiceResource = new ByteArrayResource(voiceData);
//        System.out.println(voiceResource);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("voice", voiceDTO.getVoice().getResource());
        body.add("userId", voiceDTO.getUserId());
        body.add("weId", voiceDTO.getWeId());

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers2);

        RestTemplate restTemplate = new RestTemplate();

//
//        HttpEntity<?> entity = new HttpEntity<>(voiceDTO, headers2);
        String url = "https://be06-119-194-163-123.jp.ngrok.io/voice_chat_bot_inference";
        System.out.println(voiceDTO);

//        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        ResponseEntity<?> resultMap = restTemplate.postForEntity(url, requestEntity, byte[].class);
        System.out.println("resultMap = " + resultMap);

        if(resultMap.getBody() == null){
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "postVoiceChatBot failed", responseMap));
        }

        responseMap.put("voice",resultMap);


        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"postVoiceChatBot succeed",responseMap));
    }
    //음성채팅 업로드
//    @PostMapping("/postvoice/{username}")
//    public ResponseEntity<?> postVoice(@PathVariable String username , @RequestBody VoiceDTO voiceDTO) throws UnsupportedAudioFileException, IOException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        Map<String, Object> responseMap = new HashMap<>();
//        Voice voice = null;
//        voice = voiceService.postVoice(username, voiceDTO);
//
//        responseMap.put("voiceName",voice.getVoiceName());
//
//        return ResponseEntity
//                .created(URI.create("/" + username))
//                .headers(headers)
//                .body(new ResponseMessage(201, "postVoice succeed", responseMap));
//    }
}
