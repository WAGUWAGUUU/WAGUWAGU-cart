package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {
    private Long cartId;
    private Long userId;
    private StoreDTO store;
    private int totalPrice;
    private OptionListDTO optionList;




    }
