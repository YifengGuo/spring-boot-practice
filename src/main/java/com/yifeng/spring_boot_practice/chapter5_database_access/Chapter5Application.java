package com.yifeng.spring_boot_practice.chapter5_database_access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by guoyifeng on 1/1/19.
 */
@SpringBootApplication(scanBasePackages = {"com.yifeng.spring_boot_practice.chapter5_database_access"})
public class Chapter5Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }
}
