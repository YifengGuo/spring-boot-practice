package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.service.impl;

import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.dao.MyBatisUserDao;
import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.pojo.User;
import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yifengguo
 */
@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {

    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Override
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }
}
