package com.yjh.rememberme.admin.controller;

import com.yjh.rememberme.admin.service.AdminMemberService;
import com.yjh.rememberme.database.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminMembercontroller {
    private final AdminMemberService adminMemberService;

    @Autowired
    public AdminMembercontroller(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    @GetMapping("/member")
    public ResponseEntity<?> getAllMember(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        List<Member> memberList = null;
        memberList = adminMemberService.getAllMember();


        return ResponseEntity
                .ok()
                .headers(headers)
                .body(memberList);

    }
}
