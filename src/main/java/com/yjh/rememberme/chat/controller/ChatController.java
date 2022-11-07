package com.yjh.rememberme.chat.controller;

import com.yjh.rememberme.chat.dto.ChatDTO;
import com.yjh.rememberme.chat.service.ChatService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.LoginLog;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.LoginLogRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        responseMap.put("username",username);

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

    @GetMapping("/{username}")
    public ResponseEntity<?> getChat(@PathVariable String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<Chat> chat = chatService.getChat(username);

//        JSONObject obj = new JSONObject();
//        obj.put("chat", chat);
//        JSONArray a = new JSONArray();
//        a.add(0, obj);
//
//        System.out.println(chat);

//        List<LoginLog> log = loginLogRepository.findAllByMemberId(member.getId());

//        for (int i = 0; i < chat.size(); i++) {
//            List<Chat> chatlist=null;
//            chatlist.put(chat.get(i).getData());
//        }
//
        responseMap.put("chatData", chat);

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"getChat succeed",responseMap));
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
}
