package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.dao;

import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author yifengguo
 */
@Repository
public interface MyBatisUserDao {
    User getUser(Long id);
}
