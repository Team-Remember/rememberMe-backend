package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.TblLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblLoginLogRepository extends JpaRepository<TblLoginLog, Integer> {
}