package com.example.cart.controller;

import com.example.cart.dto.CartDTO;
import com.example.cart.service.CartServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartServiceImpl cartService;


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCart(@RequestBody CartDTO cart) throws JsonProcessingException {
        cartService.saveCart(cart);
    }

    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) throws JsonProcessingException {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }


}
