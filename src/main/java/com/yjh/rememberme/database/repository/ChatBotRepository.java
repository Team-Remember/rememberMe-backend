package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.ChatBot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatBotRepository extends JpaRepository<ChatBot, Integer> {

    int countChatBotByChatBotDateIsNotNull();
}
