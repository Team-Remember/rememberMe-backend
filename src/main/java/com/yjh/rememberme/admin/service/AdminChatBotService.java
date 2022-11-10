package com.yjh.rememberme.admin.service;

import com.yjh.rememberme.database.entity.ChatBot;
import com.yjh.rememberme.database.repository.ChatBotRepository;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminChatBotService {

    private final ChatBotRepository chatBotRepository;
    private final MemberRepository memberRepository;

    public AdminChatBotService(ChatBotRepository chatBotRepository, MemberRepository memberRepository) {
        this.chatBotRepository = chatBotRepository;
        this.memberRepository = memberRepository;
    }

    public int countAllByChatBotIdIsNotNull(){
        int count = chatBotRepository.countAllByChatBotIdIsNotNull();

        return count;
    }

}

