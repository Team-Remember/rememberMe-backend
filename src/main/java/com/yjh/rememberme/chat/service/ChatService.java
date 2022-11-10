package com.yjh.rememberme.chat.service;

import com.yjh.rememberme.chat.dto.ChatDTO;
import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.ChatRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        Member opponentId = memberRepository.findIdByNickname(chatData.getOpponentName());
        chat = chatRepository.save(new Chat(
                0,
                chatData.getData(),
                member.getId(),
                opponentId.getId()
        ));
        return chat;
    }

    @Transactional
    public List<Chat> getChat(String username, String opponentname) {
        Member member = memberRepository.findByUsername(username);
        Member opponent = memberRepository.findByNickname(opponentname);
        List<Chat> chat = chatRepository.findByMemberIdAndOpponentId(member.getId(),opponent.getId());


        System.out.println("chat"+chat);
        return chat;
    }
}