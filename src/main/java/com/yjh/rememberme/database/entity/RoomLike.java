package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_room_like")
public class RoomLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_LIKE_ID")
    private Integer id;

    @CreatedDate
    @Column(name = "ROOM_LIKE_DATE", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date roomLikeDate;

    @Column(name = "MEMBER_NICKNAME", nullable = false)
    private String memberNickname;

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName;

}
