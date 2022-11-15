package com.yjh.rememberme.auth.controller;

import com.yjh.rememberme.auth.dto.EmailDTO;
import com.yjh.rememberme.auth.dto.NicknameDTO;
import com.yjh.rememberme.auth.dto.UsernameDTO;
import com.yjh.rememberme.auth.service.DupCheckService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend
 */

@RestController
@RequestMapping("/duplication")
public class DupCheckController {
    private final DupCheckService dupCheckService;

    @Autowired
    public DupCheckController(DupCheckService dupCheckService) {
        this.dupCheckService = dupCheckService;
    }

    @PostMapping("username")
    public ResponseEntity<?> getUserNameIsDuplicated(@RequestBody UsernameDTO usernameDTO) {
        System.out.println(usernameDTO.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int result = dupCheckService.countUsername(usernameDTO);
        responseMap.put("username",usernameDTO.getUsername());

        if (result != 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(400, "username is duplicated", responseMap));
        } else {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(200, "username is available", responseMap));
        }
    }

    @PostMapping("email")
    public ResponseEntity<?> getEmailIsDuplicated(@RequestBody EmailDTO emailDTO){
        System.out.println(emailDTO.getEmail());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int result =dupCheckService.countEmail(emailDTO);
        responseMap.put("email", emailDTO.getEmail());

        if (result != 0){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(400, "email is duplicated", responseMap));
        } else {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(200, "email is available", responseMap));
        }
    }

    @PostMapping("nickname")
    public ResponseEntity<?> getEmailIsDuplicated(@RequestBody NicknameDTO nicknameDTO){
        System.out.println(nicknameDTO.getNickname());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int result =dupCheckService.countNickname(nicknameDTO);
        responseMap.put("nickname", nicknameDTO.getNickname());

        if (result != 0){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(400, "nickname is duplicated", responseMap));
        } else {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(200, "nickname is available", responseMap));
        }
    }
}
