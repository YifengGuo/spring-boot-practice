package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.controller;

import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.pojo.User;
import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yifengguo
 */
@RestController
@RequestMapping("/mybatis")
public class MyBatisController {

    @Autowired
    private MyBatisUserService myBatisUserService;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return myBatisUserService.getUser(id);
    }
}
