package com.yjh.rememberme.voice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoiceDTO {
    private Byte voiceData;
    private String opponentName;
}
