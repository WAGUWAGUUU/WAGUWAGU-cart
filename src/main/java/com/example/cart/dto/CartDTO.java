package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
// 메뉴id 추가
public class CartDTO implements Serializable {

        private String storeId;
        private String storeName;
        private List<MenuItemDTO> menuItems;
        private Long userId;
        private int totalPrice;
//        private List<OptionListDTO> selectedOptions;


    }
