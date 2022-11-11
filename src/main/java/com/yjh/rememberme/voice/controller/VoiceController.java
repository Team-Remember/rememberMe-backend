package com.yjh.rememberme.voice.controller;

import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.voice.dto.VoiceDTO;
import com.yjh.rememberme.voice.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
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

    @PostMapping("/{username}")
    public ResponseEntity<?> postVoice(@PathVariable String username , @RequestBody VoiceDTO voiceDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        voiceService.postVoiceLog(username, voiceDTO.getOpponentName());

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> entity = new HttpEntity<>(voiceDTO, headers);
        String url = "https://0ba3-119-194-163-123.jp.ngrok.io/chat_bot_train_db";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Map.class);
        System.out.println("resultMap = " + resultMap);

        responseMap.put("succeed","Yes");

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"postVoice succeed",responseMap));
    }

}
