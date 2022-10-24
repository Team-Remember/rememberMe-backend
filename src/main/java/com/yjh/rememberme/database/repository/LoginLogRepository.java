package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {
}