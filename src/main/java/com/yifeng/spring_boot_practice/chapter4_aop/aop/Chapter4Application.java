package com.yifeng.spring_boot_practice.chapter4_AOP.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by guoyifeng on 12/30/18
 */
@SpringBootApplication(scanBasePackages = {"com.yifeng.spring_boot_practice.chapter4_AOP.aop"})
public class Chapter4Application {

    // initialize aspect
    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter4Application.class, args);
    }
}
