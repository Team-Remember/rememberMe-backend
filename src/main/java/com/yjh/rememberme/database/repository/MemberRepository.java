package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    public Member findByUsername(String username);
    public int countByUsername(String username);
    public int countByEmail(String email);
    public int countByNickname(String nickname);
    public Member findById(int id);
    public Member findByNicknameAndEmail(String nickname,String email);
    public Member findByNicknameAndEmailAndUsername(String nickname, String email, String username);
    public Page<Member> findByNicknameContaining(String keyword, Pageable pageable);

    int findIdByUsername(String username);
}