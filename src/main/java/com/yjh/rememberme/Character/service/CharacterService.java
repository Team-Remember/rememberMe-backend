package com.yjh.rememberme.Character.service;

import com.yjh.rememberme.Character.dto.CharacterDTO;
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
    public Character getCharacter(String username) {
        Character character = null;
        Member member = memberRepository.findByUsername(username);
        character = characterRepository.findByMember(member);
        return character;
    }
}
