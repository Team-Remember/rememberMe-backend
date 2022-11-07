package com.yjh.rememberme.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatBotDTO {

    private Map<String, Object> data;

}
