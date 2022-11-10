package com.yjh.rememberme.admin.controller;

import com.yjh.rememberme.admin.service.AdminLoginLogService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminLogin")
public class AdminLoginLogController {

    private final AdminLoginLogService adminLoginLogService;

    @Autowired
    public AdminLoginLogController(AdminLoginLogService adminLoginLogService) {
        this.adminLoginLogService = adminLoginLogService;
    }

    @GetMapping("{memberId}")
    public ResponseEntity<?> findAllLoginLog(@PathVariable int memberId){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        List<LoginLog> loginList= adminLoginLogService.findAllByMemberId(memberId);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "OK",responseMap));
    }

    @GetMapping("/count")
    public ResponseEntity<?> countLogin(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "OK",responseMap));
    }

}
