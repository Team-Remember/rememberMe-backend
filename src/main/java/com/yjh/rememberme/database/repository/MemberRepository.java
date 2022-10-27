package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    public Member findByUsername(String username);
    public int countByUsername(String username);
    public int countByEmail(String email);
}