package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Chat;
import com.yjh.rememberme.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findChatsByMemberId(int memberId);

    List<Chat> findByMemberId(Integer id);


    List<Chat> findByMemberIdAndOpponentId(Integer id, Integer opponentId);

//    List<Chat> findAllByMemberId(int member);
//    List<Chat> findAllByMember(@Param("member") Member member);
//    List<Chat> findAllByMemberId(@Param("member") int member);
//    Chat findIdByMember(Member member);
}