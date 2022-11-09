package com.yjh.rememberme.Member.controller;

import com.yjh.rememberme.Member.dto.MatchIdDTO;
import com.yjh.rememberme.Member.dto.MatchPasswordDTO;
import com.yjh.rememberme.Member.dto.PasswordDTO;
import com.yjh.rememberme.Member.service.MemberService;
import com.yjh.rememberme.auth.service.MailService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend
 */

@RestController
@RequestMapping("/user")
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;

    @Autowired
    public MemberController(MemberService memberService, MailService mailService) {
        this.memberService = memberService;
        this.mailService = mailService;
    }

    @PostMapping("match/id")
    public void matchUsername(@RequestBody MatchIdDTO matchIdDTO) throws MessagingException {

        System.out.println("입력된 이름 : " + matchIdDTO.getNickname());
        System.out.println("입력된 이메일 : " + matchIdDTO.getEmail());

        String username = memberService.matchUsername(matchIdDTO);

        StringBuilder convertedUsername = new StringBuilder();
        convertedUsername.append(username);
        convertedUsername.replace(username.length()-3,username.length(),"***");
        System.out.println("사용자 아이디 : " + convertedUsername);

        System.out.println(matchIdDTO.getEmail() + "로 메일을 발송합니다.");
        mailService.sendEmailForMatchId(matchIdDTO.getEmail(),convertedUsername.toString());
    }

    @PostMapping("match/password")
    public void matchPassword(@RequestBody MatchPasswordDTO matchPasswordDTO) throws MessagingException {
        System.out.println("입력된 이름 : " + matchPasswordDTO.getNickname());
        System.out.println("입력된 아이디 : " + matchPasswordDTO.getUsername());
        System.out.println("입력된 이메일 : " + matchPasswordDTO.getEmail());

        String tempPassword = memberService.matchPassword(matchPasswordDTO);
        System.out.println(matchPasswordDTO.getEmail() + "로 메일을 발송합니다.");
        mailService.sendEmailForMatchPassword("pjhhs021@gmail.com",tempPassword);

    }

    @PatchMapping("/{username}")
    public ResponseEntity<?> deleteUesr(@PathVariable String username){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        int result = memberService.deleteUser(username);

        responseMap.put("result", result);

        if(result>0){
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new ResponseMessage(200, "OK",responseMap));

        }

        return ResponseEntity
                .badRequest()
                .headers(headers)
                .body(new ResponseMessage(400, "Bad Request",responseMap));

    }
    @PutMapping("/password")
    public  void putPassword(@RequestBody PasswordDTO passwordDTO){
        memberService.putPassword(passwordDTO.getUsername(),passwordDTO.getOldPassword(),passwordDTO.getNewPassword());
    }
}
