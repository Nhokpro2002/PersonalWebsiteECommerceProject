package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.entity.cart.Cart;
import com.newwave.bu3internecommerce.entity.cart.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public record CartDTO (Long cartId,
                       List<CartItemDTO> cartItemsDTO,
                       double cartTotalPrice) {
    public static CartDTO mappingFromCart(Cart cart) {
        return new CartDTO(cart.getId(),
                mappingFromCartItem(cart.getCartItems()),
                calculusTotalPrice(cart.getCartItems()));

    }

    private static List<CartItemDTO> mappingFromCartItem(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItemDTO::mappingFromCartItem)
                .collect(Collectors.toList());
    }

    private static double calculusTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getLaptop().getSellingPrice() * cartItem.getQuantity())
                .sum();
    }

}
