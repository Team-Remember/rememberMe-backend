package com.yjh.rememberme.object.dto;

import com.yjh.rememberme.database.vo.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDTO {
    private int idx;
    Position position;
    private Double angle;
    private Double scaleValue;
}
