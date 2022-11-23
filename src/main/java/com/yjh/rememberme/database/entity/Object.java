package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_OBJECT")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_ID", nullable = false)
    private Integer id;

    @Column(name = "OBJECT_IDX", nullable = false)
    private int idx;

    @Column(name = "OBJECT_POSITION_X", nullable = false)
    private Double positionX;

    @Column(name = "OBJECT_POSITION_Y", nullable = false)
    private Double positionY;

    @Column(name = "OBJECT_POSITION_Z", nullable = false)
    private Double positionZ;

    @Column(name = "OBJECT_Y_ANGLE", nullable = false)
    private Double yAngle;

    @Column(name = "OBJECT_SCALE_VALUE", nullable = false)
    private Double scaleValue;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


}