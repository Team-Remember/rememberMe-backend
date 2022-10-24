package com.yjh.rememberme.database.repository;

import com.yjh.rememberme.database.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object, Integer> {
}