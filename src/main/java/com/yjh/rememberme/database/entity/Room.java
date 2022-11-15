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
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @Column(name = "room_name", nullable = false, unique = true)
    private String roomName;

    @Column(name = "room_status", nullable = false)
    private String roomStatus;

    @Column(name ="room_likes", nullable = false)
    private int roomLikes;

    @Column(name = "room_views", nullable = false)
    private int roomViews;

    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;
}
