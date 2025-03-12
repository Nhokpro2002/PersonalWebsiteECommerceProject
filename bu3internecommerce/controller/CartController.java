package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.response.CartDTO;
import com.newwave.bu3internecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Add laptop to Shopping Cart
     * @param cartId The cartId of Shopping Cart
     * @param laptopId The laptopId of Laptop
     * @param quantity The laptop quantity be added to Shopping Cart
     * @return result after add laptop to Cart: Success/Fail
     */
    @PostMapping("/{cartId}/add/{laptopId}/{quantity}")
    public ResponseEntity<String> addToCart(
            @PathVariable Long cartId,
            @PathVariable Long laptopId,
            @PathVariable int quantity) {
        cartService.addToCart(cartId, laptopId, quantity);
        return ResponseEntity.ok("Laptop added to cart successfully");
    }

    /**
     * Remove laptop to Shopping Cart
     * @param cartId The cartId of Shopping Cart
     * @param laptopId The laptopId of Laptop
     * @param quantity The laptop quantity be added to Shopping Cart
     * @return result after remove laptop from Cart: Success/Fail
     */
    @PatchMapping("/{cartId}/remove/{laptopId}/{quantity}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long cartId,
            @PathVariable Long laptopId,
            @PathVariable int quantity) {
        cartService.removeFromCart(cartId, laptopId, quantity);
        return ResponseEntity.ok("Laptop remove from cart successfully");
    }

    /**
     * Get total price of Shopping Cart
     * @param cartId The cartId of Shopping Cart
     * @return total price of Shopping Cart by cartId
     */
    @GetMapping("/{cartId}/total-price")
    public double getCartTotalPrice(@PathVariable Long cartId) {
        return cartService.getCartTotalPrice(cartId);
    }

    /**
     * Get information about Shopping Cart by cartId
     * @param cartId The cartId of Shopping Cart
     * @return information cart items in Shopping Cart
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }


}
