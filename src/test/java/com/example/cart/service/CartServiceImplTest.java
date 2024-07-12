package com.example.cart.service;

import com.example.cart.dto.CartDTO;
import com.example.cart.dto.MenuItemDTO;
import com.example.cart.dto.StoreDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private RedisTemplate<String, CartDTO> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void save() throws JsonProcessingException {
        // Create test data
        UUID uuid = UUID.randomUUID();
        CartDTO cartDTO = new CartDTO(
                "DFD",
                uuid.toString(),
                new StoreDTO(1L, "Test Store"),

                List.of(
                        new MenuItemDTO(1L, "Item 1", 10, 2),
                        new MenuItemDTO(2L, "Item 2", 20, 1)
                ),
                40
        );

        // Save the cart
        cartService.saveCart(cartDTO);

        // Verify the data is saved in Redis
        ValueOperations<String, CartDTO> operations = redisTemplate.opsForValue();
        CartDTO cartDTO1 = operations.get(uuid.toString());

        // Assert the cart data is saved correctly
        assertTrue(cartDTO1 != null);
        assertTrue(cartDTO1.getUserId().equals(cartDTO.getUserId()));
        assertTrue(cartDTO1.getTotalPrice() == cartDTO.getTotalPrice());

        System.out.println("Cart saved in Redis: " + cartDTO1);
    }



    class Test1{
        private String test;

        @Override
        public String toString() {
            return "{" +
                    "test='" + test + '\'' +
                    '}';
        }

        public Test1(String test) {
            this.test = test;
        }

        public String getTest() {
            return test;
        }
    }
}