package com.yjh.rememberme.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    @NotNull
    private String opponentName;
    @NotNull
    private List<Map<String, String>> data;
}

