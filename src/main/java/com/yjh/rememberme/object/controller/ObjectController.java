package com.yjh.rememberme.object.controller;

import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Object;
import com.yjh.rememberme.object.dto.DatasDTO;
import com.yjh.rememberme.object.service.ObjectService;
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
@RequestMapping("/object")
public class ObjectController {
    private final ObjectService objectService;

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    //오브젝트 등록
    @PostMapping("/{roomid}")
    public ResponseEntity<?> postObjects(@PathVariable int roomid, @RequestBody DatasDTO datas) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, java.lang.Object> responseMap = new HashMap<>();

        objectService.deleteObjects(roomid);
        objectService.postObjects(roomid,datas);

        responseMap.put("roomId", roomid);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201,"postObjects Succeed", responseMap));
    }
    @GetMapping("/{roomid}")
    public ResponseEntity<?> getObjects(@PathVariable int roomid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, List<Object>> responseMap = new HashMap<>();

        List<Object> objectList = objectService.getObjects(roomid);

        responseMap.put("datas", objectList);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(responseMap);
    }
}
