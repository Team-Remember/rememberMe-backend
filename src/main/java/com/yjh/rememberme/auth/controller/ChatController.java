package com.yjh.rememberme.auth.controller;

import com.yjh.rememberme.auth.dto.ChatDTO;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository, MemberRepository memberRepository) {
        this.chatRepository = chatRepository;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> postChat(@PathVariable String username, @RequestBody ChatDTO chatData) throws ParseException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        Chat chat = null;
        Member member = memberRepository.findByUsername(username);
//        JSONParser jsonParser = new JSONParser();
//        JSONArray jsonArray = new JSONArray();
//        jsonArray = (JSONArray) jsonParser.parse(chatData.getChatContents().toString());
        try{
            chat = chatRepository.save(new Chat(
                    0,
                    chatData.getData(),
                    member
            ));
        } catch (Exception e) {
            System.out.println(e);
            responseMap.put("inputUsername",username);
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "chat not posted",responseMap));
        }

        responseMap.put("chatId",chat.getId());
        responseMap.put("memberId",chat.getMember());

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"chat posted",responseMap));
    }

    @PostMapping("/chat_bot")
    public ResponseEntity
}
