package com.yjh.rememberme.object.controller;

import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.object.dto.DatasDTO;
import com.yjh.rememberme.object.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/object")
public class ObjectController {
    private final ObjectService objectService;

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @PostMapping("/{roomid}")
    public ResponseEntity<?> postObjects(@PathVariable int roomid, @RequestBody DatasDTO datas) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        objectService.deleteObjects(roomid);
        objectService.postObjects(roomid,datas);

        responseMap.put("roomId", roomid);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(201,"postObjects Succeed", responseMap));
    }
}
