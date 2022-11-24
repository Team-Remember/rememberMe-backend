package com.yjh.rememberme.voice.service;

import com.yjh.rememberme.common.s3.S3VoiceFile;
import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.database.entity.VoiceLog;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.VoiceLogRepository;
import com.yjh.rememberme.database.repository.VoiceRepository;
import com.yjh.rememberme.voice.dto.VoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Service
public class VoiceService {
    private final VoiceRepository voiceRepository;
    private final VoiceLogRepository voiceLogRepository;
    private final MemberRepository memberRepository;
    private final S3VoiceFile s3VoiceFile;

    @Autowired
    public VoiceService(VoiceRepository voiceRepository, VoiceLogRepository voiceLogRepository, MemberRepository memberRepository, S3VoiceFile s3VoiceFile) {
        this.voiceRepository = voiceRepository;
        this.voiceLogRepository = voiceLogRepository;
        this.memberRepository = memberRepository;
        this.s3VoiceFile = s3VoiceFile;
    }

    @Transactional
    public void postVoiceLog(String username, String opponentNickname) {
        VoiceLog voiceLog = null;
        try {
            voiceLog = voiceLogRepository.save(new VoiceLog(0,
                    new java.sql.Date(new Date().getTime()),
                    memberRepository.findByUsername(username).getId(),
                    memberRepository.findByUsername(opponentNickname).getId()
            ));
        } catch (Exception e){
            System.out.println(e);
            System.out.println("postVoiceLog fail");
        }

    }

    public Voice postVoice(String username, VoiceDTO voiceDTO) throws UnsupportedAudioFileException, IOException {
        Voice voice = null;
        String voiceName = UUID.randomUUID() + "-voice_" + memberRepository.findByUsername(username).getId() + ".wav";
        String voicePath = s3VoiceFile.upload(voiceDTO.getVoice(), voiceName, "voice");

        voice = voiceRepository.save(new Voice(
                0,
                new java.sql.Date(new Date().getTime()),
                voicePath,
                voiceName,
                memberRepository.findByNickname(voiceDTO.getUserNickname()).getId(),
                memberRepository.findByNickname(voiceDTO.getOpponentNickname()).getId()
        ));
        return voice;
    }

    public MultiValueMap<String, Object> addBody(VoiceDTO voiceDTO) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("voice", voiceDTO.getVoice().getResource());
        body.add("userId", memberRepository.findByNickname(voiceDTO.getUserNickname()).getId());
        body.add("weId", memberRepository.findByNickname(voiceDTO.getOpponentNickname()).getId());

        return body;
    }

    public Map<String, Object> putMap(String username, String opponentname) {
        Map<String, Object> map = new HashMap<>();
        int userId = memberRepository.findByNickname(username).getId();
        int weId = memberRepository.findByNickname(opponentname).getId();
        map.put("userId",userId);
        map.put("weId",weId);
        map.put("voicePathList",voiceRepository.findAllByMemberIdAndOpponentId(userId,weId));

        return map;
    }

    public ResponseEntity<?> callSTT(VoiceDTO voiceDTO, int userId, int weId) {
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        headers2.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body = addBody(voiceDTO);

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers2);

        RestTemplate restTemplate = new RestTemplate();

        String url = "ai stt 서버 url";
        System.out.println("body = " + body);

        ResponseEntity<?> resultMap = restTemplate.postForEntity(url, requestEntity, byte[].class);

        return resultMap;
    }

    public ResponseEntity<?> callChatBot(ResponseEntity<?> resultMap, int userId, int weId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> body = new HashMap<>();

        body.put("data", resultMap);
        body.put("userId", userId);
        body.put("weId", weId);

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://9e5a-119-194-163-123.jp.ngrok.io/chat_bot";

        ResponseEntity<?> resultMap2 = restTemplate.postForEntity(url, requestEntity, Map.class);

        return resultMap2;
    }

    public ResponseEntity<?> callTTS(ResponseEntity<?> resultMap2, int userId, int weId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        Map<String, Object> body = new HashMap<>();

        body.put("data", resultMap2);
        body.put("userId", userId);
        body.put("weId", weId);

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://9e5a-119-194-163-123.jp.ngrok.io/chat_bot";

        ResponseEntity<?> resultMap3 = restTemplate.postForEntity(url, requestEntity, byte[].class);

        return resultMap3;
    }
}
