package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {
    private String cartId;
    private String userId;

    private StoreDTO store;
    private List<MenuItemDTO> menuItems;
    private int totalPrice;


    }
