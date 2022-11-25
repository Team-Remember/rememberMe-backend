package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectRepository extends JpaRepository<Object, Integer> {
    List<Object> findAllByRoomId(int roomid);
}