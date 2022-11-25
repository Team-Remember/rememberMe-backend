package com.yjh.rememberme.database.repository.dto;

import com.yjh.rememberme.object.dto.PositionDTO;
import lombok.Data;
import lombok.Getter;


public interface GetObjectDTO {
    int getIdx();
    getPositionDTO getPosition();
    Double getAngle();
    Double getScaleValue();
}
