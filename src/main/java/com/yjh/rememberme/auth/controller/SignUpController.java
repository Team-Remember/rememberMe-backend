package com.yjh.rememberme.auth.controller;

import com.yjh.rememberme.auth.dto.SignUpDTO;
import com.yjh.rememberme.auth.service.SignUpService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend
 */

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }
    @Operation(description = "회원 가입")
    @PostMapping
    public ResponseEntity<?> postSignUp(@RequestBody SignUpDTO signUp) {
        System.out.println("회원 가입api");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        Member member = null;

        try {
            member = signUpService.postSignUp(signUp);
        } catch (Exception e){
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
