package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", nullable = false)
    private Integer id;

    @Column(name = "ROOM_NAME", nullable = false, unique = true)
    private String roomName;

    @Column(name = "ROOM_STATUS", nullable = false)
    private String roomStatus;

    @Column(name ="ROOM_LIKES", nullable = false)
    private int roomLikes;

    @Column(name = "ROOM_VIEWS", nullable = false)
    private int roomViews;

    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;
}
