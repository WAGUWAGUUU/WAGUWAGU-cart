package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionListDTO {
    private Long listId;
    private String listName;
    private List<OptionDTO> options;
}
