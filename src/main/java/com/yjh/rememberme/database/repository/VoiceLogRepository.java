package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.VoiceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceLogRepository extends JpaRepository<VoiceLog, Integer> {
}