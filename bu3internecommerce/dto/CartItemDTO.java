package com.newwave.bu3internecommerce.dto;


import lombok.Getter;

@Getter
public class CartItemDTO {
    private Long productId;
    private int quantity;
    private double price;

    public CartItemDTO(Long productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}

