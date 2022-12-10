package com.yjh.rememberme.character.service;

import com.yjh.rememberme.character.dto.CharacterDTO;
import com.yjh.rememberme.database.entity.Character;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.CharacterRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, MemberRepository memberRepository) {
        this.characterRepository = characterRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Character postCharacter(String username, CharacterDTO characterData) {

        Character character = null;
        Member member = memberRepository.findByUsername(username);
        character = characterRepository.save(new Character(
                0,
                characterData.getGender(),
                characterData.getHairNum(),
                characterData.getJacketNum(),
                characterData.getChestNum(),
                characterData.getTieNum(),
                characterData.getLegsNum(),
                characterData.getFeetNum(),
                member
        ));
        return character;
    }

    @Transactional
    public Character putCharacter(String username, CharacterDTO characterData) {

        Character character = null;
        Member member = memberRepository.findByUsername(username);
        character = characterRepository.findByMember(member);
        character.setGender((characterData.getGender()));
        character.setChestNum(characterData.getChestNum());
        character.setFeetNum(characterData.getFeetNum());
        character.setLegsNum(characterData.getLegsNum());
        character.setHairNum(characterData.getHairNum());
        character.setJacketNum(characterData.getJacketNum());
        character.setTieNum(characterData.getTieNum());

        return character;
    }

    @Transactional
    public Character getCharacter(String nickname) {
        Character character = null;
        Member member = memberRepository.findByNickname(nickname);
        character = characterRepository.findByMember(member);
        return character;
    }
}
