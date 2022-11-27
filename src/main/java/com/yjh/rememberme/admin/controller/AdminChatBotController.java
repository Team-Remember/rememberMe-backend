package com.yjh.rememberme.admin.controller;

import com.yjh.rememberme.admin.service.AdminChatBotService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminChatBot")
public class AdminChatBotController {

    private final AdminChatBotService adminChatBotService;

    public AdminChatBotController(AdminChatBotService adminChatBotService) {
        this.adminChatBotService = adminChatBotService;
    }
    @Operation(description = "챗봇 요청 횟수")
    @GetMapping
    public ResponseEntity<?> countChatBotRequest(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int count = adminChatBotService.countAllByChatBotIdIsNotNull();

        responseMap.put("countChatBot", count);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200,"OK",responseMap));

    }
}
