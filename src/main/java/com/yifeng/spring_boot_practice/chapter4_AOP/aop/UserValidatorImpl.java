package com.yifeng.spring_boot_practice.chapter4_AOP.aop;

/**
 * Created by guoyifeng on 12/31/18
 */
public class UserValidatorImpl implements UserValidator {
    public boolean validate(User user) {
        System.out.println("Invoke validator interface: " + UserValidator.class.getSimpleName());
        return user != null;
    }
}
