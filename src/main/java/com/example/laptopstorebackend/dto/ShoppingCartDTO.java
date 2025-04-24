package com.example.laptopstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private List<ShoppingCartItemDTO> items;
    private BigInteger totalPrice;
}
