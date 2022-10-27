package com.yjh.rememberme.auth.controller;

import com.yjh.rememberme.auth.dto.SignUpDTO;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpController(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUp){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        Member member = null;

        try {
            member = memberRepository.save(new Member(0,
                    signUp.getUsername(),
                    passwordEncoder.encode(signUp.getPassword()),
                    new java.sql.Date(new Date().getTime()),
                    signUp.getEmail(),
                    Member.Role.USER
                    ));
        } catch (Exception e){
            System.out.println(e);
            responseMap.put("inputUserName",signUp.getUsername());
            responseMap.put("inputEmail",signUp.getEmail());
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "user not created", responseMap));
        }
        responseMap.put("userName",member.getUsername());
        responseMap.put("email",member.getEmail());

        return ResponseEntity
                .created(URI.create("/users/"+signUp.getUsername()))
                .headers(headers)
                .body(new ResponseMessage(201, "user created",responseMap));
    }
}
