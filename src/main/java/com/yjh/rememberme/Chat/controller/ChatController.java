package com.yjh.rememberme.Chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjh.rememberme.Chat.dto.ChatBotDTO;
import com.yjh.rememberme.Chat.dto.ChatDTO;
import com.yjh.rememberme.Chat.service.ChatService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/{username}")
    public ResponseEntity<?> postChat(@PathVariable String username, @RequestBody ChatDTO chatData) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        Chat chat = null;
        chat = chatService.postChat(username, chatData);

        responseMap.put("chatId",chat.getId());
        responseMap.put("memberId",chat.getMember().getUsername());



        if (chat==null) {
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "postChat failed", responseMap));
        }

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"chat posted",responseMap));
    }

    @PostMapping("/{username}/chat_bot")
    public ResponseEntity<?> postChatBot(@PathVariable String username, @RequestBody String data) throws Exception{

        System.out.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
//        Chat chat = null;
//        chat = chatService.postChatBot(username, chatBotData);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> entity = new HttpEntity<>(data, headers);
        String url = "https://7b62-119-194-163-123.jp.ngrok.io/chat_bot";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
        responseMap.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        responseMap.put("header", resultMap.getHeaders()); //헤더 정보 확인
        responseMap.put("body", resultMap.getBody()); //실제 데이터 정보 확인


        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"chatBot posted",responseMap));
    }

//    public String getData(String url ) {
//        //Spring restTemplate
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        String jsonInString = "";
//
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders header = new HttpHeaders();
//        HttpEntity<?> entity = new HttpEntity<>(header);
//
//        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
//
//        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);
//
//        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
//        result.put("header", resultMap.getHeaders()); //헤더 정보 확인
//        result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
//
//        //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
//        ObjectMapper mapper = new ObjectMapper();
//        jsonInString = mapper.writeValueAsString(resultMap.getBody());
//
//
//        return jsonInString;
//    }

}
