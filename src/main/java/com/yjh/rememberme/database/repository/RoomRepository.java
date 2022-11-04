package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}