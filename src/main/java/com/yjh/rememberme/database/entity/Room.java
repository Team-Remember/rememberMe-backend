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
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Column(name ="ROOM_LIKES")
    private int roomLikes;

    @Column(name = "ROOM_VIEWS")
    private int roomViews;

    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;

    @Column(name = "MEMBER_NICKNAME", nullable = false)
    private String memberNickname;

    public enum RoomStatus {
        PUBLIC("public"), PRIVATE("private");

        private final String text;
        RoomStatus(String text) {this.text = text;}

        public String getText() { return text; }

    }
}
