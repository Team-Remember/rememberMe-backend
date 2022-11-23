package com.yjh.rememberme.room.service;

import com.yjh.rememberme.database.entity.Room;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, MemberRepository memberRepository) {
        this.roomRepository = roomRepository;
        this.memberRepository = memberRepository;
    }
    @Transactional
    public int createRoom(String username, String roomname) {
        try{ roomRepository.save(new Room(
                0,
                roomname,
                Room.RoomStatus.PUBLIC,
                0,
                0,
                memberRepository.findByNickname(username).getId()
        ));
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("postRoom fail");
        }
        return roomRepository.findByRoomName(roomname).getId();
    }
    @Transactional
    public Room.RoomStatus patchRoomPrivate(String username, String roomname) {
        Room roomStatus2 = roomRepository.findByRoomNameAndMemberId(roomname, memberRepository.findByUsername(username).getId());
        roomStatus2.setRoomStatus(Room.RoomStatus.PRIVATE);
        roomRepository.save(roomStatus2);
        return roomStatus2.getRoomStatus();
    }
    @Transactional
    public Room.RoomStatus patchRoomPublic(String username, String roomname) {
        Room roomStatus2 = roomRepository.findByRoomNameAndMemberId(roomname, memberRepository.findByUsername(username).getId());
        roomStatus2.setRoomStatus(Room.RoomStatus.PUBLIC);
        roomRepository.save(roomStatus2);
        return roomStatus2.getRoomStatus();
    }
}
