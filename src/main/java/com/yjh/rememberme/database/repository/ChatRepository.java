package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}