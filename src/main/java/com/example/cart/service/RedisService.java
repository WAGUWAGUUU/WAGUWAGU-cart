package com.example.cart.service;

import com.example.cart.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<Long, CartDTO> redisTemplate;

    public void save(Long key, CartDTO value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public CartDTO find(Long key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete (Long key) {
        redisTemplate.delete(key);
    }




}
