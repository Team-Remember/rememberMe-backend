package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.TblCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblCharacterRepository extends JpaRepository<TblCharacter, Integer> {
}