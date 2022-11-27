package com.yjh.rememberme.room.controller;

import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Room;
import com.yjh.rememberme.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    // 방생성
    @GetMapping
    public ResponseEntity<?> createRoom(@RequestParam("username") String username, @RequestParam("roomname") String roomname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int roomId = roomService.createRoom(username, roomname);

        responseMap.put("roomId",roomId);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201,"postRoom Succeed", responseMap));
    }
    @GetMapping("/roomlist")
    public ResponseEntity<?> getRoomList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<Room> roomList = roomService.getRoomList();

        responseMap.put("roomList",roomList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "postRoom Succeed", responseMap));
    }
    // 방 불러오기 1
    @GetMapping("/withroomid")
    public ResponseEntity<?> getRoomByRoomId(@RequestParam("roomid") int roomid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Room room = roomService.getRoomByRoomId(roomid);

        responseMap.put("room",room);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "postRoom Succeed", responseMap));
    }
    // 방 불러오기 2
    @GetMapping("/withroomname")
    public ResponseEntity<?> getRoomByRoomIdAndMemberId(@RequestParam("roomname") String roomname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Room room = roomService.getRoomByRoomName(roomname);

        responseMap.put("room",room);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201, "postRoom Succeed", responseMap));
    }
    // Private방으로 바꾸기
    @PatchMapping("/private")
    public ResponseEntity<?> patchRoomPrivate(@RequestParam("username") String username, @RequestParam("roomname") String roomname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Room.RoomStatus roomStatus = roomService.patchRoomPrivate(username, roomname);

        responseMap.put("roomStatus", roomStatus);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201,"patchRoomPrivate Succeed", responseMap));
    }
    // Public방으로 바꾸기
    @PatchMapping("/public")
    public ResponseEntity<?> patchRoomPublic(@RequestParam("username") String username, @RequestParam("roomname") String roomname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Room.RoomStatus roomStatus = roomService.patchRoomPublic(username, roomname);

        responseMap.put("roomStatus", roomStatus);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201,"patchRoomPublic Succeed", responseMap));
    }
}
