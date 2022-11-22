package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Voice;
import com.yjh.rememberme.database.repository.dto.VoicePath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoiceRepository extends JpaRepository<Voice, Integer> {

    List<VoicePath> findAllByMemberIdAndOpponentId(int memberId, int opponentId);

}