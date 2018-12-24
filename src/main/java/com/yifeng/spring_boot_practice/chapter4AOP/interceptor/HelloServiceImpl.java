package com.yifeng.spring_boot_practice.chapter4AOP.interceptor;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by guoyifeng on 12/22/18
 */
public class HelloServiceImpl implements HelloService {
    public void sayHello(String name) {
        if (!StringUtils.isEmpty(name)) {
            System.out.println("Hello, " + name + "!");
        } else {
            throw new IllegalArgumentException("name cannout be null or empty...");
        }
    }
}
