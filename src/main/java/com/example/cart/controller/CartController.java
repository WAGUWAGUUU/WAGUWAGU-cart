package com.example.cart.controller;

import com.example.cart.dto.CartDTO;
import com.example.cart.dto.MenuItemDTO;
import com.example.cart.service.CartServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {

   private final CartServiceImpl cartService;


    @CrossOrigin(origins = "http://192.168.0.150:8081")
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCartItem(@RequestBody CartDTO cart) throws JsonProcessingException {

        cartService.saveCart(cart);
    }


    @CrossOrigin(origins = "http://192.168.0.150:8081")
    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) throws JsonProcessingException {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }

    @DeleteMapping("/delete/{userId}/{itemId}")
    public void deleteCart(@PathVariable Long userId, @PathVariable Long itemId) {

    }
}
