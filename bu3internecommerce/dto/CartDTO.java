package com.newwave.bu3internecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {
    private Long cartId;
    private List<CartItemDTO> cartItems;
    private double price;

    public CartDTO(Long cartId, List<CartItemDTO> cartItems, double price) {
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.price = price;
    }


}
