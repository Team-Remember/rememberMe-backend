package com.yjh.rememberme.room.service;

import com.yjh.rememberme.database.entity.Room;
import com.yjh.rememberme.database.entity.RoomLike;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.RoomLikeRepository;
import com.yjh.rememberme.database.repository.RoomRepository;
import com.yjh.rememberme.room.dto.RoomLikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    private final RoomLikeRepository roomLikeRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, MemberRepository memberRepository, RoomLikeRepository roomLikeRepository) {
        this.roomRepository = roomRepository;
        this.memberRepository = memberRepository;
        this.roomLikeRepository = roomLikeRepository;
    }
    @Transactional
    public int createRoom(String username, String roomname) {
        try{ roomRepository.save(new Room(
                0,
                roomname,
                Room.RoomStatus.PUBLIC,
                0,
                0,
                memberRepository.findByUsername(username).getId(),
                memberRepository.findByUsername(username).getNickname()
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

    @Transactional
    public Room getRoomByRoomId(int roomid) {
        return roomRepository.findById(roomid);
    }

    @Transactional
    public Room getRoomByRoomName(String roomname) {
        return roomRepository.findByRoomName(roomname);
    }

    @Transactional
    public List<Room> getRoomList() {
        return roomRepository.findAll();
    }


    //방 좋아요 service
    @Transactional
    public void createRoomLike(RoomLikeDTO roomLikeData) {

        Optional<RoomLike> like = Optional.ofNullable(roomLikeRepository.findByRoomNameAndMemberNickname(roomLikeData.getRoomName(), roomLikeData.getMemberNickname()));
        RoomLike like2 = roomLikeRepository.findByRoomNameAndMemberNickname(roomLikeData.getRoomName(), roomLikeData.getMemberNickname());

        if(like.isEmpty()){
            try{
                roomLikeRepository.save(new RoomLike(
                        0,
                        new Date(),
                        roomLikeData.getMemberNickname(),
                        roomLikeData.getRoomName()
            ));
            }catch (Exception e) {
                System.out.println(e);
                System.out.println("RoomLike save fail");
            }
            roomRepository.updateLikes(roomLikeData.getRoomName());
        }
        else{
            try{
                roomLikeRepository.deleteById(like2.getId());
            }catch (Exception e) {
                System.out.println(e);
                System.out.println("RoomLike delete fail");
            }
            roomRepository.deleteLikes(roomLikeData.getRoomName());
        }
    }

    public int addRoomViews(int roomid) {
        Room room = roomRepository.findById(roomid);
        int views = roomRepository.updateViews(roomid);
        return views;
    }
    public int addRoomViewsByRoomname(String roomname) {
        Room room = roomRepository.findByRoomName(roomname);
        int views = roomRepository.updateViews(roomname);
        return views;
    }
}
