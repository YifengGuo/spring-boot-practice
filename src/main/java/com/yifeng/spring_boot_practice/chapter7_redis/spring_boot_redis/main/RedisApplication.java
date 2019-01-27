package com.yifeng.spring_boot_practice.chapter7_redis.spring_boot_redis.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

/**
 * @author yifengguo
 */
@SpringBootApplication(scanBasePackages = "com.yifeng.spring_boot_practice.chapter7_redis.spring_boot_redis")
public class RedisApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        initRedisTempate();
    }

    private void initRedisTempate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
