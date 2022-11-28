package com.yjh.rememberme.common.controller;

import com.yjh.rememberme.common.dto.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class RootController {
    @RequestMapping("")
    public ResponseEntity<?> common (){

        HttpHeaders headers = new HttpHeaders(); //헤더 생성
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8"))); //header contentType 설정
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("result", "api request success");


        return ResponseEntity
                .ok()
                .body(new ResponseMessage(201, "Success", responseMap));
    }
}
