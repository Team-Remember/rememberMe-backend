package com.yjh.rememberme.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private Double positionX;
    private Double positionY;
    private Double positionZ;
}
