package com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.service;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.pojo.User;

import java.util.List;

/**
 * Created by guoyifeng on 1/2/19.
 */
public interface JdbcTemplateUserService {

    User getUser(Long id);

    List<User> findUsers(String username, String note);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);
}
