package com.yjh.rememberme.auth.controller;

import com.yjh.rememberme.auth.dto.LoginDTO;
import com.yjh.rememberme.auth.util.JwtUtil;
import com.yjh.rememberme.common.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend
 */

@RestController
@RequestMapping("/login")
public class LoginController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginInfo){
        System.out.println(loginInfo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        String token = null;
        Boolean selectCategory = false;

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginInfo.getUsername(),
                            loginInfo.getPassword()
                    )
            );
        } catch (Exception e){
            System.out.println(e);
            responseMap.put("inputId", loginInfo.getUsername());
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "login failed", responseMap));
        }
        token = jwtUtil.generateToken(loginInfo.getUsername());
        responseMap.put("id",loginInfo.getUsername());
        responseMap.put("token",token);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200,"token generated", responseMap));
    }
}
