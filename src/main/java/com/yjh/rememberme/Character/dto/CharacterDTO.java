package com.yjh.rememberme.Character.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {
    private String nickname;
    private String hairNum;
    private String jacketNum;
    private String chestNum;
    private String tieNum;
    private String legsNum;
    private String feetNum;
}
