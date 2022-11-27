package com.yjh.rememberme.auth.controller;


import com.yjh.rememberme.auth.dto.EmailDTO;
import com.yjh.rememberme.auth.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend/blob/main/src/main/java/com/steady/leisurethatapi/auth/controller/MailController.java
 */

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    @PostMapping("/match")
    public int sendEmail(@RequestBody EmailDTO toEmail) throws MessagingException {
        System.out.println(toEmail.getEmail() + "로 메일을 발송합니다.");
        int certCode = mailService.sendEmail(toEmail.getEmail());
        return certCode;
    }
}
