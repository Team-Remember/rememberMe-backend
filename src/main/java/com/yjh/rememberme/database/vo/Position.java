package com.yjh.rememberme.database.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Data
@Embeddable
public class Position {
    @Column(name = "OBJECT_POSITION_X")
    private Double x;

    @Column(name = "OBJECT_POSITION_Y")
    private Double y;

    @Column(name = "OBJECT_POSITION_Z")
    private Double z;
}
