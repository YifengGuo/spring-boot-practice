package com.yifeng.spring_boot_practice.chapter7_redis.spring_redis.main;

/**
 * @author yifengguo
 */

import com.yifeng.spring_boot_practice.chapter7_redis.spring_redis.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Some simple tests on chapter 7
 */
public class Main {
    public static void main(String[] args) {
        // load bean with its config to the Spring Ioc
        // get this bean by AnnotationConfigApplicationContext
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate<Object, Object> redisTemplate = ctx.getBean(RedisTemplate.class);

        // use redis-cli
        // > keys *key1 to get the value
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "hkey", "hvalue");
    }
}
