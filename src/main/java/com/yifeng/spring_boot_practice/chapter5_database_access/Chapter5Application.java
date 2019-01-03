package com.yifeng.spring_boot_practice.chapter5_database_access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by guoyifeng on 1/1/19.
 */
@SpringBootApplication(scanBasePackages = {"com.yifeng.spring_boot_practice.chapter5_database_access"})
@EnableJpaRepositories(basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.dao")
@EntityScan(basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.pojo")
public class Chapter5Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }
}
