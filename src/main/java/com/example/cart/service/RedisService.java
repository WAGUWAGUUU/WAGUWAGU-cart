package com.example.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    public void save(UUID key, String value) {
        redisTemplate.opsForValue().set(key.toString(), value);
    }

    public String find(UUID key) {
        return redisTemplate.opsForValue().get(key.toString());
    }
}
