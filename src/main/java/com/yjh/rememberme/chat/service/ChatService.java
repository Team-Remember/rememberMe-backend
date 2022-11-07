package com.yjh.rememberme.chat.service;

import com.yjh.rememberme.chat.dto.ChatDTO;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

//    @Transactional
//    public List<Chat> getChat(String username) {
//        Member member = memberRepository.findByUsername(username);
//        List<Chat> chat = chatRepository.findAllByMember(member);
//
//        System.out.println("chat"+chat);
//        return chat;
//    }
}