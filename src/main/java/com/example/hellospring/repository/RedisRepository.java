package com.example.hellospring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final LettuceConnectionFactory redisConnectionFactory;
    private RedisTemplate<String, String> redisTemplate;
    ValueOperations<String, String> ops;

    @PostConstruct
    public void init() {
        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        ops = redisTemplate.opsForValue();
        redisTemplate.afterPropertiesSet();
    }

    public String save(String value) {
        String key = UUID.randomUUID().toString();
        ops.set(key, value);
        return key;
    }

    public String findByKey(String key) {
        return ops.get(key);
    }
}
