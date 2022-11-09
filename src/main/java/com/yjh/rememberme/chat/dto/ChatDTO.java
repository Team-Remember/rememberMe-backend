package com.yjh.rememberme.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private String opponentName;
    private List<Map<String, String>> data;
}

