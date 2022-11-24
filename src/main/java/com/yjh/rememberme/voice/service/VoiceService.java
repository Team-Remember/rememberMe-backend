package com.yjh.rememberme.voice.service;

import com.yjh.rememberme.common.s3.S3VoiceFile;
import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.database.entity.VoiceLog;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.VoiceLogRepository;
import com.yjh.rememberme.database.repository.VoiceRepository;
import com.yjh.rememberme.voice.dto.PostVoiceDTO;
import com.yjh.rememberme.voice.dto.VoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VoiceService {
    private final VoiceRepository voiceRepository;
    private final VoiceLogRepository voiceLogRepository;
    private final MemberRepository memberRepository;
    private final S3VoiceFile s3VoiceFile;

    @Autowired
    public VoiceService(VoiceRepository voiceRepository, VoiceLogRepository voiceLogRepository, MemberRepository memberRepository, S3VoiceFile s3VoiceFile) {
        this.voiceRepository = voiceRepository;
        this.voiceLogRepository = voiceLogRepository;
        this.memberRepository = memberRepository;
        this.s3VoiceFile = s3VoiceFile;
    }

    @Transactional
    public void postVoiceLog(String username, String opponentNickname) {
        VoiceLog voiceLog = null;
        try {
            voiceLog = voiceLogRepository.save(new VoiceLog(0,
                    new java.sql.Date(new Date().getTime()),
                    memberRepository.findByUsername(username).getId(),
                    memberRepository.findByUsername(opponentNickname).getId()
            ));
        } catch (Exception e){
            System.out.println(e);
            System.out.println("postVoiceLog fail");
        }

    }

    public Voice postVoice(String username, PostVoiceDTO postVoiceDTO) throws UnsupportedAudioFileException, IOException {
        Voice voice = null;
        String voiceName = UUID.randomUUID() + "-voice_" + memberRepository.findByUsername(username).getId() + ".wav";
        String voicePath = s3VoiceFile.upload(postVoiceDTO.getVoice(), voiceName, "voice");

        voice = voiceRepository.save(new Voice(
                0,
                new java.sql.Date(new Date().getTime()),
                voicePath,
                voiceName,
                memberRepository.findByNickname(postVoiceDTO.getUserNickname()).getId()
        ));
        return voice;
    }

    public MultiValueMap<String, Object> addBody(VoiceDTO voiceDTO) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("voice", voiceDTO.getVoice().getResource());
        body.add("userId", memberRepository.findByUsername(voiceDTO.getUserNickname()).getId());
        body.add("weId", memberRepository.findByUsername(voiceDTO.getOpponentNickname()).getId());

        return body;
    }

    public Map<String, Object> putMap(String username) {
        Map<String, Object> map = new HashMap<>();
        int userId = memberRepository.findByNickname(username).getId();
        map.put("userId",userId);
        map.put("voicePathList",voiceRepository.findAllByMemberId(userId));

        return map;
    }
}
