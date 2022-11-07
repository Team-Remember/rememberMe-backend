package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    void save(int memberId);

    List<Chat>  findAllByMember(Member member);
    Chat findIdByMember(Member member);
}