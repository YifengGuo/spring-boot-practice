package com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.dao;

import com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by guoyifeng on 1/3/19.
 */
public interface JpaUserRepository extends JpaRepository<User, Long> {
}
