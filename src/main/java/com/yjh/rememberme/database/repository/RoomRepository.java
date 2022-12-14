package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;


public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByRoomName(String roomname);

    Room findByRoomNameAndMemberId(String roomname, Integer id);

    Room findById(int roomid);

    @Transactional
    @Modifying
    @Query("update Room r set r.roomLikes = r.roomLikes + 1 where r.roomName = :room_name")
    void updateLikes(@Param(value="room_name") String roomname);

    @Transactional
    @Modifying
    @Query("update Room r set r.roomLikes = r.roomLikes - 1 where r.roomName = :room_name")
    void deleteLikes(@Param(value="room_name") String roomname);

    @Transactional
    @Modifying
    @Query("update Room set roomViews = roomViews + 1 where id = :room_id")
    int updateViews(@Param(value = "room_id") int roomid);
    @Transactional
    @Modifying
    @Query("update Room set roomViews = roomViews + 1 where roomName = :room_name")
    int updateViews(@Param(value = "room_name") String roomname);

}