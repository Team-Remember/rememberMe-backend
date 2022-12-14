package com.yjh.rememberme.admin.service;

import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public AdminMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Transactional
    public List<Member> getAllMember() {
        List<Member> member = memberRepository.findAll();
        List<Member> memberList = new ArrayList<>();

        for (Member value : member) {
            memberList.add(Member.builder()
                    .id(value.getId())
                    .username(value.getUsername())
                    .nickname(value.getNickname())
                    .regDate(value.getRegDate())
                    .status(value.getStatus())
                    .email(value.getEmail())
                    .role(value.getRole())
                    .build());
        }

        return memberList;
    }
    @Transactional
    public Member getMember(String username) {
        Member foundMember = null;
        foundMember = memberRepository.findByUsername(username);
        Member member = new Member();
        member.setId(foundMember.getId());
        member.setUsername(foundMember.getUsername());
        member.setNickname(foundMember.getNickname());
        member.setRegDate(foundMember.getRegDate());
        member.setEmail(foundMember.getEmail());
        member.setRole(foundMember.getRole());
        member.setStatus(foundMember.getStatus());


        return member;
    }

    public long countMember() {
        long count = 0;
        count = memberRepository.count();

        return count;
    }

    public long countByStatus(String status) {
        long count = 0;
        count = memberRepository.countByStatus(status);

        return count;
    }
}
