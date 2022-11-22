package com.yjh.rememberme.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TBL_OBJECT")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_ID", nullable = false)
    private Integer id;


    @Column(name = "OBJECT_PLACE_A")
    private String objectPlaceA;


    @Column(name = "OBJECT_PLACE_B")
    private String objectPlaceB;


    @Column(name = "OBJECT_PLACE_C")
    private String objectPlaceC;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


}