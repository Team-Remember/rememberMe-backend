package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.RoomLike;
import com.yjh.rememberme.room.dto.RoomLikeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomLikeRepository extends JpaRepository<RoomLike, Integer> {

    RoomLike findByRoomNameAndMemberNickname(String roomName, String memberNickname);

}
