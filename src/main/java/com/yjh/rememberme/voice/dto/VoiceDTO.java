package com.yjh.rememberme.voice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class VoiceDTO {
    @NotNull
    private String userId;
    @NotNull
    private String weId;
    @NotNull
    private byte[] voice;
}
