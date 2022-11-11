package com.yjh.rememberme.voice.service;

import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.VoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoiceService {
    private final VoiceRepository voiceRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public VoiceService(VoiceRepository voiceRepository, MemberRepository memberRepository) {
        this.voiceRepository = voiceRepository;
        this.memberRepository = memberRepository;
    }

    public Voice postVoiceLog(String username, String opponentName) {
        Voice voice = null;
        try {
            voice = voiceRepository.save(new Voice(0,
                    new java.sql.Date(new Date().getTime()),
                    memberRepository.findByUsername(username).getId(),
                    memberRepository.findIdByNickname(opponentName).getId()
            ));
        } catch (Exception e){
            System.out.println(e);
            System.out.println("postVoiceLog fail");
        }

        return voice;
    }
}
