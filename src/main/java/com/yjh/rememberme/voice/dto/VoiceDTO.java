package com.yjh.rememberme.voice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoiceDTO {
    private String userId;
    private String weId;
    private MultipartFile voice;

//    private MultipartFile form;
}
