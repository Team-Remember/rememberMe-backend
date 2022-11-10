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

        List<Member> member = null;
        member = memberRepository.findAll();
        List<Member> memberList = new ArrayList<>();

        for (int i = 0; i < member.size(); i++) {
            memberList.add(Member.builder()
                            .id(member.get(i).getId())
                            .username(member.get(i).getUsername())
                            .nickname(member.get(i).getNickname())
                            .regDate(member.get(i).getRegDate())
                            .status(member.get(i).getStatus())
                            .email(member.get(i).getEmail())
                            .role(member.get(i).getRole())
                    .build());
        }

        return memberList;
    }
}
