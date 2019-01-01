package com.yifeng.spring_boot_practice.chapter4_AOP.aop;

import org.springframework.stereotype.Service;

/**
 * Created by guoyifeng on 12/30/18
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * assume this is join point that will be intercepted
     * @param user
     */
    public void printUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null!");
        }
        System.out.println("id = " + user.getId());
        System.out.println("username = " + user.getUsername());
        System.out.println("note = " + user.getNote());
    }
}
