package com.yjh.rememberme.database.entity;

import com.yjh.rememberme.database.vo.Position;
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

    @Column(name = "OBJECT_IDX")
    private int idx;

    @Embedded
    private Position position;

    @Column(name = "OBJECT_ANGLE")
    private Double angle;

    @Column(name = "OBJECT_SCALE_VALUE")
    private Double scaleValue;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


}