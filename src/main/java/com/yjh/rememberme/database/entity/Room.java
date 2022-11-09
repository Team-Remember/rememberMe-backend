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
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "room_name", nullable = false, unique = true)
    private String roomName;

    @NotNull
    @Column(name = "room_status", nullable = false)
    private String roomStatus;

    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;
}
