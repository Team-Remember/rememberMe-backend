package com.yjh.rememberme.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDTO {
    private int idx;
    PositionDTO position;
    private Double yAngler;
    private Double scaleValue;
}
