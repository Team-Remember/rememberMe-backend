package com.yjh.rememberme.voice.service;

import com.yjh.rememberme.common.s3.S3VoiceFile;
import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.database.entity.VoiceLog;
import com.yjh.rememberme.database.repository.MemberRepository;
import com.yjh.rememberme.database.repository.VoiceLogRepository;
import com.yjh.rememberme.database.repository.VoiceRepository;
import com.yjh.rememberme.voice.dto.VoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
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
    public void postVoiceLog(String username, String weId) {
        VoiceLog voiceLog = null;
        try {
            voiceLog = voiceLogRepository.save(new VoiceLog(0,
                    new java.sql.Date(new Date().getTime()),
                    memberRepository.findByUsername(username).getId(),
                    memberRepository.findByUsername(weId).getId()
            ));
        } catch (Exception e){
            System.out.println(e);
            System.out.println("postVoiceLog fail");
        }

    }

    public Voice postVoice(String username, VoiceDTO voiceDTO) throws UnsupportedAudioFileException, IOException {
        Voice voice = null;
        byte[] voiceByteArray = voiceDTO.getVoice();
        String voiceName = UUID.randomUUID() + "-voice_" + memberRepository.findByUsername(username).getId() + ".wave";
        String voicePath = s3VoiceFile.upload(voiceByteArray, voiceName, "voice");

        voice = voiceRepository.save(new Voice(
                0,
                new java.sql.Date(new Date().getTime()),
                memberRepository.findByUsername(username).getUsername(),
                memberRepository.findByNickname(voiceDTO.getWeId()).getUsername(),
                voicePath,
                voiceName
        ));
        return voice;
    }
}
