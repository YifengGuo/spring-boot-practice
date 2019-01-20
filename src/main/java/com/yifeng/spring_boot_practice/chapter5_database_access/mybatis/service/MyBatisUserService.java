package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.service;

import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.pojo.User;

/**
 * @author yifengguo
 */
public interface MyBatisUserService {
    User getUser(Long id);

}
