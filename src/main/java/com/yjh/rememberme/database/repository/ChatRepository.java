package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    void save(int memberId);

    List<Chat> findAllByMember(@Param("member") Member member);
    Chat findIdByMember(Member member);
}