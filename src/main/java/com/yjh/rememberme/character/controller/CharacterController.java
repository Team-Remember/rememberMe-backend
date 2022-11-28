package com.yjh.rememberme.character.controller;

import com.yjh.rememberme.character.dto.CharacterDTO;
import com.yjh.rememberme.character.service.CharacterService;
import com.yjh.rememberme.Member.service.MemberService;
import com.yjh.rememberme.common.dto.ResponseMessage;
import com.yjh.rememberme.database.entity.Character;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "캐릭터 등록")
    @PostMapping("/{username}")
    public ResponseEntity<?> postCharacter(@PathVariable String username, @RequestBody CharacterDTO characterData) {
        System.out.println("캐릭터 등록");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Character character = null;
        character = characterService.postCharacter(username, characterData);

        if (character==null) {
            responseMap.put("username", username);
            responseMap.put("nickname", characterData.getNickname());

            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "putCharacter failed", responseMap));
        }

        responseMap.put("characterId",character.getId());
        responseMap.put("memberNickname",character.getMember().getNickname());

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"character posted",responseMap));
    }
    @Operation(description = "캐릭터 수정")
    @PutMapping("/{username}")
    public ResponseEntity<?> putCharacter(@PathVariable String username, @RequestBody CharacterDTO characterData) {
        System.out.println("캐릭터 수정");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Character character = null;
        character= characterService.putCharacter(username, characterData);

        if (character==null) {
            responseMap.put("username", username);
            responseMap.put("nickname", characterData.getNickname());

            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "putCharacter failed", responseMap));
        }

        responseMap.put("characterId",character.getId());
        responseMap.put("memberNickname",character.getMember().getNickname());

        return ResponseEntity
                .created(URI.create("/"+username))
                .headers(headers)
                .body(new ResponseMessage(201,"putCharacter succeed",responseMap));
    }
    @Operation(description = "캐릭터 불러오기")
    @GetMapping("find/{nickname}")
    public ResponseEntity<?> getCharacter(@PathVariable String nickname) {
        System.out.println("캐릭터 불러오기");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        Character character = null;
        character = characterService.getCharacter(nickname);

        if (character==null) {
            responseMap.put("nickename", nickname);

            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body(new ResponseMessage(400, "putCharacter failed", responseMap));
        }

        responseMap.put("nickname", nickname);
        responseMap.put("hairNum", character.getHairNum());
        responseMap.put("jacketNum", character.getJacketNum());
        responseMap.put("chestNum", character.getChestNum());
        responseMap.put("tieNum", character.getTieNum());
        responseMap.put("legsNum", character.getLegsNum());
        responseMap.put("feetNum", character.getFeetNum());


        return ResponseEntity
                .created(URI.create("/"+nickname))
                .headers(headers)
                .body(new ResponseMessage(201,"character finding succeed ",responseMap));
    }
}
