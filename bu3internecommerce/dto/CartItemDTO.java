package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.entity.cart.CartItem;

public record CartItemDTO (LaptopDTO laptopResponseDTO,
                           int quantity,
                           double totalPrice) {

    public static CartItemDTO mappingFromCartItem(CartItem cartItem) {
        return new CartItemDTO(LaptopDTO.mappingFromLaptop(cartItem.getLaptop()),
                cartItem.getQuantity(),
                cartItem.getLaptop().getSellingPrice() * cartItem.getQuantity());

    }
}

