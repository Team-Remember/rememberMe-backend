package com.yjh.rememberme.admin.service;

import com.yjh.rememberme.database.entity.LoginLog;
import com.yjh.rememberme.database.repository.LoginLogRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoginLogService {

    private final LoginLogRepository loginLogRepository;
    private final MemberRepository memberRepository;

    public AdminLoginLogService(LoginLogRepository loginLogRepository, MemberRepository memberRepository) {
        this.loginLogRepository = loginLogRepository;
        this.memberRepository = memberRepository;
    }

    public List<LoginLog> findAllByMemberId(Integer memberId) {

        List<LoginLog> loginList = loginLogRepository.findAllByMemberId(memberId);

        return loginList;
    }

    public int countByMemberId(Integer memberId) {

        int count = loginLogRepository.countByMemberId(memberId);

        return count;
    }
}
