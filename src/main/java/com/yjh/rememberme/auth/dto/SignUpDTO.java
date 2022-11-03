package com.yjh.rememberme.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String username;
    private String nickname;
    private String email;
    private String password;
}
