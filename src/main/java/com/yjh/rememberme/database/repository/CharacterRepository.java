package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}