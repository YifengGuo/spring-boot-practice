package com.yifeng.spring_boot_practice.chapter7_redis.spring_redis.main;

/**
 * @author yifengguo
 */

import com.yifeng.spring_boot_practice.chapter7_redis.spring_redis.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * Some simple tests on chapter 7
 */
public class Main {
    public static void main(String[] args) {
        // load bean with its config to the Spring Ioc
        // get this bean by AnnotationConfigApplicationContext
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate<Object, Object> redisTemplate = ctx.getBean(RedisTemplate.class);

        naiveInsertData(redisTemplate);

        insertDataWithRedisCallback(redisTemplate);

        insertDataWithSessionCallback(redisTemplate);
    }

    /**
     * Each operation on redis would create a new redis connection
     * use RedisCallback or SessionCallback to run multiple operations within one redis connection
     * @param redisTemplate
     */
    private static void naiveInsertData(RedisTemplate redisTemplate) {
        // use redis-cli
        // > keys *key1 to get the value
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "hkey", "hvalue");
    }

    /**
     * much more low-level than SessionCallback, NOT RECOMMENDED
     * @param redisTemplate
     */
    private static void insertDataWithRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection rc) -> {
            rc.set("key2".getBytes(), "value2".getBytes());
            rc.hSet("hash".getBytes(), "hkey2".getBytes(), "hvalue2".getBytes());
            return null;
        });
    }

    private static void insertDataWithSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations ro) throws DataAccessException {
                ro.opsForValue().set("key2", "value2");
                ro.opsForHash().put("hash", "hkey2", "hvalue2");
                return null;
            }
        });
    }
}
