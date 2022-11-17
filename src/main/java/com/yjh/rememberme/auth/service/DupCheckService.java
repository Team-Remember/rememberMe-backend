package com.yjh.rememberme.auth.service;

import com.yjh.rememberme.auth.dto.EmailDTO;
import com.yjh.rememberme.auth.dto.NicknameDTO;
import com.yjh.rememberme.auth.dto.UsernameDTO;
import com.yjh.rememberme.database.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DupCheckService {
    private final MemberRepository memberRepository;

    public DupCheckService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Transactional
    public int countUsername(UsernameDTO usernameDTO) {
        int result = 0;
        result = memberRepository.countByUsername(usernameDTO.getUsername());

        return result;
    }
    @Transactional
    public int countEmail(EmailDTO emailDTO) {
        int result = 0;
        result = memberRepository.countByEmail(emailDTO.getEmail());

        return result;
    }
    @Transactional
    public int countNickname(NicknameDTO nicknameDTO) {
        int result = 0;
        result = memberRepository.countByNickname(nicknameDTO.getNickname());

        return result;
    }
}
