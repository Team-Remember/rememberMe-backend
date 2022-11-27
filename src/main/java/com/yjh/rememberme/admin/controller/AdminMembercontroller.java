package com.yjh.rememberme.admin.controller;

import com.yjh.rememberme.admin.service.AdminMemberService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "모든 회원 불러오기")
    @GetMapping("/member")
    public ResponseEntity<?> getAllMember(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        List<Member> memberList = null;
        memberList = adminMemberService.getAllMember();

        responseMap.put("MemberList", memberList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "getAllMember succeed",responseMap));
    }
    @Operation(description = "회원 정보 보기")
    @GetMapping("/{username}")
    public ResponseEntity<?> getMember(@PathVariable String username){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Member member = null;
        member = adminMemberService.getMember(username);

        responseMap.put("Member", member);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "getMember succeed",responseMap));
    }
    @Operation(description = "회원 수 불러오기")
    @GetMapping("/count")
    public ResponseEntity<?> countMember(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        long count = 0;
        count = adminMemberService.countMember();

        responseMap.put("MemberCount", count);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "countMember succeed",responseMap));
    }
    @Operation(description = "Status별 회원 수")
    @GetMapping("/count/{status}")
    public ResponseEntity<?> countByStatus(@PathVariable String status){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        long count = 0;
        count = adminMemberService.countByStatus(status);

        responseMap.put("MemberCount", count);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "countByStatus succeed",responseMap));
    }
}
