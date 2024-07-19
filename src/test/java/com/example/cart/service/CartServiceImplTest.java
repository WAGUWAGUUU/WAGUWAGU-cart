package com.example.cart.service;

import com.example.cart.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
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
        OptionDTO option1 = new OptionDTO(1L, "Extra Cheese", 50, false);
        OptionDTO option2 = new OptionDTO(2L, "Pepperoni", 75, false);

        OptionListDTO optionList1 = new OptionListDTO(1L, "Toppings", Arrays.asList(option1, option2));
        List<OptionListDTO> optionLists = Arrays.asList(optionList1);



        CartDTO cartDTO = new CartDTO(
                "DFD",
                uuid.toString(),
                new StoreDTO(1L, "Test Store"),

                List.of(
                        new MenuItemDTO(1L, "Item 1", 10, 2,optionLists),
                        new MenuItemDTO(2L, "Item 2", 20, 1,optionLists)
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