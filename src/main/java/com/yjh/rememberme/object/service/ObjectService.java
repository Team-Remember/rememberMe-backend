package com.yjh.rememberme.object.service;

import com.yjh.rememberme.database.entity.Object;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.ObjectRepository;
import com.yjh.rememberme.database.repository.RoomRepository;
import com.yjh.rememberme.database.repository.dto.GetObjectDTO;
import com.yjh.rememberme.object.dto.DatasDTO;
import com.yjh.rememberme.object.dto.ObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class ObjectService {
    private final MemberRepository memberRepository;
    private final ObjectRepository objectRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ObjectService(MemberRepository memberRepository, ObjectRepository objectRepository, RoomRepository roomRepository) {
        this.memberRepository = memberRepository;
        this.objectRepository = objectRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void postObjects(int roomid, DatasDTO datas) {
        List<ObjectDTO> data = datas.getDatas();
        for (ObjectDTO datum : data) {
            objectRepository.save(new Object(
                    0,
                    datum.getIdx(),
                    datum.getPosition(),
                    datum.getAngle(),
                    datum.getScaleValue(),
                    roomRepository.findById(roomid)
            ));
        }
    }
    @Transactional
    public void deleteObjects(int roomid) {
        objectRepository.deleteByRoomId(roomid);
    }

    public List<GetObjectDTO> getObjects(int roomid) {

        List<GetObjectDTO> objects = objectRepository.findAllByRoomId(roomid);

        return objects;
    }
}
