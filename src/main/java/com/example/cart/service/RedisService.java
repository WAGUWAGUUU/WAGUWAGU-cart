package com.example.cart.service;

import com.example.cart.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RedisService {
    @Autowired
    private final RedisTemplate<Long, CartDTO> redisTemplate;

    public void delete(Long key) {


        redisTemplate.delete(key);
        Boolean b = redisTemplate.hasKey(key);
        System.out.println(b);
    }

    // Additional methods for saving and finding CartDTO
    public void save(Long key, CartDTO cartDTO) {
        String stringKey = key.toString();
        redisTemplate.opsForValue().set(key, cartDTO);
    }

    public CartDTO find(Long key) {
        String stringKey = key.toString();
        return redisTemplate.opsForValue().get(stringKey);
    }




}
