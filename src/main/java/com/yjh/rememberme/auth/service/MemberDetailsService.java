package com.yjh.rememberme.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public interface MemberDetailsService extends UserDetailsService {
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
