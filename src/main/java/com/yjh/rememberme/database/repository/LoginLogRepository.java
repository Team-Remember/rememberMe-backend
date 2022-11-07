package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.LoginLog;
import com.yjh.rememberme.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {

    List<LoginLog> findAllByMemberId(int member);
    List<Integer> findByMemberId(int id);
}