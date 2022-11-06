package com.yjh.rememberme.Chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjh.rememberme.Chat.dto.ChatDTO;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MemberRepository memberRepository) {
        this.chatRepository = chatRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Chat postChat(String username, ChatDTO chatData) {

        Chat chat = null;
        Member member = memberRepository.findByUsername(username);
        chat = chatRepository.save(new Chat(
                0,
                chatData.getData(),
                member
        ));
        return chat;
    }

    public Map<String,Object> postChatBot(String username, ChatDTO chatData) {


        return null;
    }

//    @Transactional
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
