package com.example.laptopstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemDTO {

    private ProductDTO productDTO;
    private Integer productQuantity;

}
