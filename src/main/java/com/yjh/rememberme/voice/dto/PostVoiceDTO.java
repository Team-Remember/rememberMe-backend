package com.yjh.rememberme.voice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostVoiceDTO {
    private String userNickname;
    private MultipartFile voice;

//    private MultipartFile form;
}
