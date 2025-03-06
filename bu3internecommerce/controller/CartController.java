package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.CartDTO;
import com.newwave.bu3internecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Add laptop to Shopping Cart
     * @param cartId The Shopping Cart
     * @param productId The Laptop
     * @param quantity The laptop quantity
     * @return result
     */
    @PostMapping("/{cartId}/add/{productId}/{quantity}")
    public ResponseEntity<String> addToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @PathVariable int quantity) {
        cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Laptop added to cart successfully");
    }

    /**
     *
     * @param cartId
     * @param productId
     * @param quantity
     * @return
     */
    @PatchMapping("/{cartId}/remove/{productId}/{quantity}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @PathVariable int quantity) {
        cartService.removeFromCart(cartId, productId, quantity);
        return ResponseEntity.ok("Laptop remove from cart successfully");
    }

    /**
     *
     * @param cartId
     * @return
     */
    @GetMapping("/{cartId}/total-price")
    public double getCartTotalPrice(@PathVariable Long cartId) {
        return cartService.getCartTotalPrice(cartId);
    }

    /**
     *
     * @param cartId
     * @return
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }


}
