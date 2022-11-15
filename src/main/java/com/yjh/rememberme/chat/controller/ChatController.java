package com.yjh.rememberme.chat.controller;

import com.yjh.rememberme.chat.dto.ChatBotDTO;
import com.yjh.rememberme.chat.dto.ChatDTO;
import com.yjh.rememberme.chat.service.ChatService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.repository.MemberRepository;
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
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final MemberRepository memberRepository;

    @Autowired
    public ChatController(ChatService chatService, MemberRepository memberRepository) {
        this.chatService = chatService;
        this.memberRepository = memberRepository;
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

    @GetMapping("/")
    public ResponseEntity<?> getChat(@RequestParam("username") String username, @RequestParam("opponentname") String opponentname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<Chat> chat = chatService.getChat(username, opponentname);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> entity = new HttpEntity<>(chat, headers);
        String url = "https://0ba3-119-194-163-123.jp.ngrok.io/chat_bot_train_db";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Map.class);
        System.out.println("resultMap = " + resultMap);

        responseMap.put("succeed","Yes");

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"getChat succeed",responseMap));
    }

    //AI 챗봇 컨트롤러
    @PostMapping("/{username}/chat_bot")
    public ResponseEntity<?> postChatBot(@PathVariable String username, @RequestBody ChatBotDTO chatBotData) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

//        System.out.println(chatBotData);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = "https://f5ef-119-194-163-123.jp.ngrok.io/chat_bot";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("chatRequest", chatBotData.getChatRequest())
                .queryParam("memberId", memberRepository.findByNickname(chatBotData.getMemberNickname()).getId())
                .queryParam("weId", memberRepository.findByNickname(chatBotData.getWeNickname()).getId())
                .build();


        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

        //챗봇 데이터가 null이면 에러처리
        if(resultMap.getBody() == null){
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "chatBot failed", responseMap));
        }

        responseMap.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        responseMap.put("header", resultMap.getHeaders()); //헤더 정보 확인
        responseMap.put("body", resultMap.getBody()); //실제 데이터 정보 확인

        chatService.saveChatBot(chatBotData);

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"chatBot succeeded",responseMap));
    }
}
