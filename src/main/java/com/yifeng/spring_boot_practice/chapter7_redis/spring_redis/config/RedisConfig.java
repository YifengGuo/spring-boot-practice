package com.yifeng.spring_boot_practice.chapter7_redis.spring_redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yifengguo
 */
@Configuration
public class RedisConfig {

    private RedisConnectionFactory redisConnectionFactory;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {

        if (this.redisConnectionFactory != null) return redisConnectionFactory;

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(30);  // count of max idle connections
        jedisPoolConfig.setMaxTotal(50);  // count of max connections
        jedisPoolConfig.setMaxWaitMillis(2000);  // miliseconds of max waiting time

        // initialize JedisConnectionFactory
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        // fetch standalone redis configuration
        RedisStandaloneConfiguration redisStandaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
//        redisStandaloneConfiguration.setPassword("root");

        this.redisConnectionFactory = jedisConnectionFactory;
        return redisConnectionFactory;
    }

    @Bean(name = "RedisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        // set serializer for string
        RedisSerializer stringRedisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
