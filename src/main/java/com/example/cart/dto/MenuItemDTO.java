package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO implements Serializable {
    private Long menuId;
    private String menuName;
    private int menuPrice;
    private int quantity;
//    private List<OptionsDto> optionsDto;



}
