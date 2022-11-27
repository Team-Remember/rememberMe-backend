package com.yjh.rememberme.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private Double x;
    private Double y;
    private Double z;
}
