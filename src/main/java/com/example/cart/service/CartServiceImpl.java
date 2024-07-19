package com.example.cart.service;


import com.example.cart.dto.CartDTO;
import com.example.cart.kafka.dto.KafkaCartDTO;
import com.example.cart.kafka.dto.KafkaStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl  {

    private final RedisService redisService;


    private final ObjectMapper objectMapper;

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

        if(msg.status().equals("create")) redisService.delete(msg.data().customerId());
        System.out.println(msg.data().customerId() );
        System.out.println("Cart deleted");

    }





}
