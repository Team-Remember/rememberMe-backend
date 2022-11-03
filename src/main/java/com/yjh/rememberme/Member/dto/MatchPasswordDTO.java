package com.yjh.rememberme.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchPasswordDTO {
    private String nickname;
    private String username;
    private String email;
}
