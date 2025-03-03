package com.newwave.bu3internecommerce.controller;


import com.newwave.bu3internecommerce.dto.LaptopDTO;
import com.newwave.bu3internecommerce.model.Cart;
import com.newwave.bu3internecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    /**
     * Add product to Shopping Cart
     * @param cartId Shopping Cart is retrieved by cartId
     * @param productId Product be added by productId
     * @return Cart after updated
     */
    @PostMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId) {
        Cart updatedCart = cartService.addProductToCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Remove product from Shopping Cart
     * @param cartId Shopping Cart is retrieved by cartId
     * @param productId Product be removed by productId
     * @return Cart after updated
     */
    @DeleteMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(
            @PathVariable Long cartId,
            @PathVariable Long productId) {
        Cart updatedCart = cartService.removeProductInCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }


    /**
     * get all laptops in Cart
     * @param cartId Shopping Cart is retrieved by cartId
     * @return all laptops information in Shopping Cart
     */
    @GetMapping("/cart/{cartId}/products")
    public List<LaptopDTO> showAllProducts(@PathVariable Long cartId) {
        return cartService.getAll(cartId);
    }

    /**
     * Get total price of Shopping Cart
     * @param cartId Shopping Cart is retrieved by cartId
     * @return total price f Shopping Cart
     */
    @GetMapping("/cart/{cartId}/price")
    public double getTotalPriceOfCart(@PathVariable Long cartId) {
        return cartService.getTotalPrice(cartId);
    }

    /**
     * Delete all laptops in Shopping Cart
     * @param cartId Shopping Cart is retrieved by cartId
     * @return Shopping Cart after delete all laptops
     */
    @DeleteMapping("/cart/{cartId}/")
    public ResponseEntity<Cart> deleteAll(@PathVariable Long cartId) {
        Cart updatedCart = cartService.removeAll(cartId);
        return ResponseEntity.ok(updatedCart);
    }
}
