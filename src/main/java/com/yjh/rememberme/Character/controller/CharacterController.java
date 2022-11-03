package com.yjh.rememberme.Character.controller;

import com.yjh.rememberme.Character.dto.CharacterDTO;
import com.yjh.rememberme.Character.service.CharacterService;
import com.yjh.rememberme.Member.service.MemberService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;
    private final MemberService memberService;

    @Autowired
    public CharacterController(CharacterService characterService, MemberService memberService) {
        this.characterService = characterService;
        this.memberService = memberService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> postCharacter(@PathVariable String username, @RequestBody CharacterDTO characterData) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Character character = null;
        character = characterService.postCharacter(username, characterData);

        responseMap.put("characterId",character.getId());
        responseMap.put("memberNickname",character.getMember().getNickname());

        if (character==null) {
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "postCharacter failed", responseMap));
        }

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"character posted",responseMap));
    }

    @GetMapping("find/{username}")
    public ResponseEntity<?> postChat(@PathVariable String username) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        Character character = null;
        character = characterService.getCharacter(username);
        String nickname = memberService.findNicknameByUsername(username);

        responseMap.put("nickname", nickname);
        responseMap.put("hairNum", character.getHairNum());
        responseMap.put("jacketNum", character.getJacketNum());
        responseMap.put("chestNum", character.getChestNum());
        responseMap.put("tieNum", character.getTieNum());
        responseMap.put("legsNum", character.getLegsNum());
        responseMap.put("feetNum", character.getFeetNum());

        if (character==null) {
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "getCharacter failed", responseMap));
        }

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"character finding succeed ",responseMap));
    }
}
