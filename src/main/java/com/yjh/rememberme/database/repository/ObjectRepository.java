package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Object;
import com.yjh.rememberme.database.repository.dto.GetObjectDTO;
import com.yjh.rememberme.object.dto.ObjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectRepository extends JpaRepository<Object, Integer> {
    List<GetObjectDTO> findAllByRoomId(int roomid);

    void deleteByRoomId(int roomid);
}