package com.yjh.rememberme.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomLikeDTO {
    private String memberNickname;
    private String roomName;
}
