package com.yjh.rememberme.auth.service;

import com.yjh.rememberme.auth.dto.SignUpDTO;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SignUpService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member postSignUp(SignUpDTO signUp) {
        Member member= null;
        member = memberRepository.save(new Member(0,
                signUp.getUsername(),
                signUp.getNickname(),
                passwordEncoder.encode(signUp.getPassword()),
                new java.sql.Date(new Date().getTime()),
                signUp.getEmail(),
                Member.Role.USER,
                "Y"
        ));
        return member;
    }
}
