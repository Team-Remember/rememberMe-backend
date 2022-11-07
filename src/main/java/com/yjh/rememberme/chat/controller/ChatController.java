package com.yjh.rememberme.chat.controller;

import com.yjh.rememberme.chat.dto.ChatDTO;
import com.yjh.rememberme.chat.service.ChatService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.LoginLogRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final LoginLogRepository loginLogRepository;
    private final MemberRepository memberRepository;
    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatService chatService, MemberRepository memberRepository, LoginLogRepository loginLogRepository, ChatRepository chatRepository) {
        this.chatService = chatService;
        this.loginLogRepository = loginLogRepository;
        this.memberRepository = memberRepository;
        this.chatRepository = chatRepository;
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

    @GetMapping("/{username}")
    public ResponseEntity<?> getChat(@PathVariable String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        Member member = memberRepository.findByUsername(username);


        List<Chat> chat = chatRepository.findAllByMember(member);

//        JSONObject obj = new JSONObject();
//        obj.put("chat", chat);
//        JSONArray a = new JSONArray();
//        a.add(0, obj);
//
//        System.out.println(chat);

//        List<LoginLog> log = loginLogRepository.findAllByMemberId(member.getId());

        responseMap.put("chatData",chat);

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"getChat succeed",responseMap));
    }
}
