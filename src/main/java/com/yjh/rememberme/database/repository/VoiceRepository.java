package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Voice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}