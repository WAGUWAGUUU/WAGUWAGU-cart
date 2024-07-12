package com.example.cart.service;


import com.example.cart.dto.CartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl  {
    @Autowired
    private RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveCart(CartDTO cart) throws JsonProcessingException {
        String userId = cart.getUserId();
        if (userId == null) {
            throw new IllegalArgumentException("Cart userId cannot be null");
        }

        String cartJson = objectMapper.writeValueAsString(cart);
        UUID key = UUID.fromString(userId);
        redisService.save(key, cartJson);
    }
    public CartDTO getCart(String userId) throws JsonProcessingException {
        UUID key = UUID.fromString(userId);
        String cartJson = redisService.find(key);

        if (cartJson != null) {
            return objectMapper.readValue(cartJson, CartDTO.class);
        } else {
            return null;
        }
    }

    public void clearCart(String userId) {
        UUID key = UUID.fromString(userId);
        redisService.save(key, null);
    }

}
