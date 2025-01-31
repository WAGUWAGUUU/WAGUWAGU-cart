package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {
    private Long optionId;
    private String optionTitle;
    private int optionPrice;
    private boolean isChecked;


}
