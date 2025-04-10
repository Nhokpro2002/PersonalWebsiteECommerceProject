
package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.ShoppingCartDTO;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.entity.ShoppingCart;
import com.example.laptopstorebackend.service.implement.ShoppingCartServiceImpl;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartServiceImpl shoppingCartServiceImpl;

    @PostMapping
    public ApiResponse<ShoppingCart> createShoppingCart(@RequestParam Long userId) {
        return ApiResponse.<ShoppingCart>builder()
                .code(200)
                .message("Create Shopping Cart successfully")
                .data(shoppingCartServiceImpl.createShoppingCart(userId))
                .build();
    }

    @GetMapping()
    public ApiResponse<ShoppingCartDTO> getAllItem(@RequestParam Long shoppingCartId) {
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("List Shopping Cart Items: ")
                .data(shoppingCartServiceImpl.getAllItem(shoppingCartId))
                .build();
    }

    @PostMapping("/items")
    public ApiResponse<ShoppingCartDTO> createShoppingCartItem(@RequestParam Long shoppingCartId,
                                                               @RequestParam Long productId,
                                                               @RequestParam Integer quantity) {
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("Create new Item successfully")
                .data(shoppingCartServiceImpl.createShoppingCartItem(shoppingCartId, productId, quantity))
                .build();

    }

    @PatchMapping("/addition")
    public ApiResponse<ShoppingCartDTO> addItem(@RequestParam Long shoppingCartId,
                                                @RequestParam Long productId) {
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("Add item successfully")
                .data(shoppingCartServiceImpl.addItem(shoppingCartId, productId))
                .build();
    }

    @PatchMapping("/reduction")
    public ApiResponse<ShoppingCartDTO> reduceItem(@RequestParam Long shoppingCartId,
                                                   @RequestParam Long productId) {
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("Reduce item successfully ")
                .data(shoppingCartServiceImpl.reduceItem(shoppingCartId, productId))
                .build();
    }

}

