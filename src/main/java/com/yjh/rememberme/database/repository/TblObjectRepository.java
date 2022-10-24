package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.TblObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblObjectRepository extends JpaRepository<TblObject, Integer> {
}