package com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.controller;

import com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.dao.JpaUserRepository;
import com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by guoyifeng on 1/3/19.
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    JpaUserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }
}
