package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.TblMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblMemberRepository extends JpaRepository<TblMember, Integer> {
}