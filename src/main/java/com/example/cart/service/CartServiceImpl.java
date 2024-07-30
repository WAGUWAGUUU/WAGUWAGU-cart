package com.example.cart.service;


import com.example.cart.dto.CartDTO;
import com.example.cart.dto.MenuItemDTO;
import com.example.cart.kafka.dto.KafkaCartDTO;
import com.example.cart.kafka.dto.KafkaStatus;
import com.example.cart.repository.CartRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CartServiceImpl  {

    private final RedisService redisService;
    private final RedisTemplate<Long, CartDTO> redisTemplate;


//    private final ObjectMapper objectMapper; - object 를 스트링으로 바꾸는 것

    public void saveCart(CartDTO cart) throws JsonProcessingException {
        Long userId = cart.getUserId();
        if (userId == null) {
            throw new IllegalArgumentException("Cart USER_Id cannot be null");
        }

        redisService.save(userId, cart);
    }



    public CartDTO getCart(Long userId) throws JsonProcessingException {
     return   redisService.find(userId);

    }

    public void clearCart(Long userId) {
        redisService.save(userId, null);
    }




    @KafkaListener(topics ="order-topic")
    public void kafkaCart(KafkaStatus<KafkaCartDTO> msg) {
        Long customerIdLong = msg.data().customerId();
        // Convert Long to String
        System.out.println("Received customerId from Kafka message: " + customerIdLong);
        System.out.println("kafka status"+msg.status());

        if (customerIdLong == null) {
            System.out.println("customer id null.. cannot delete ");
            return;
        }

        if ("CREATED".equals(msg.status())) { redisTemplate.hasKey(customerIdLong);

            redisService.delete(customerIdLong);

            System.out.println("deleted customerId from Kafka message: " + customerIdLong);
        }


        System.out.println("deleted if false ;" + redisTemplate.hasKey(customerIdLong));
    }



}
