
package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.ShoppingCartDTO;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.entity.ShoppingCart;
import com.example.laptopstorebackend.service.implement.JwtServiceImpl;
import com.example.laptopstorebackend.service.implement.ShoppingCartServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartServiceImpl shoppingCartServiceImpl;
    private final JwtServiceImpl jwtServiceImpl;

    @PostMapping
    public ApiResponse<ShoppingCart> createShoppingCart(@RequestParam Long userId) {
        return ApiResponse.<ShoppingCart>builder()
                .code(200)
                .message("Create Shopping Cart successfully")
                .data(shoppingCartServiceImpl.createShoppingCart(userId))
                .build();
    }

    @GetMapping()
    public ApiResponse<ShoppingCartDTO> getAllItem(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        Long userId = jwtServiceImpl.extractUserId(token);
        // UserId == ShoppingCartId
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("List Shopping Cart Items: ")
                .data(shoppingCartServiceImpl.getAllItem(userId))
                .build();
    }

    @PostMapping("/items")
    public ApiResponse<ShoppingCartDTO> createShoppingCartItem(HttpServletRequest request,
                                                               @RequestParam Long productId,
                                                               @RequestParam Integer quantity
                                                               ) {
        String token = extractTokenFromHeader(request);
        Long userId = jwtServiceImpl.extractUserId(token);
        // UserId == ShoppingCartId
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("Create new Item successfully")
                .data(shoppingCartServiceImpl.createShoppingCartItem(userId, productId, quantity))
                .build();
    }

    @PatchMapping("/addition")
    public ApiResponse<ShoppingCartDTO> addItem(HttpServletRequest request,
                                                @RequestParam Long productId) {
        String token = extractTokenFromHeader(request);
        Long userId = jwtServiceImpl.extractUserId(token);
        // UserId == ShoppingCartId
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("Add item successfully")
                .data(shoppingCartServiceImpl.addItem(userId, productId))
                .build();
    }

    @PatchMapping("/reduction")
    public ApiResponse<ShoppingCartDTO> reduceItem(HttpServletRequest request,
                                                   @RequestParam Long productId) {

        String token = extractTokenFromHeader(request);
        Long userId = jwtServiceImpl.extractUserId(token);
        // UserId == ShoppingCartId
        return ApiResponse.<ShoppingCartDTO>builder()
                .code(200)
                .message("Reduce item successfully ")
                .data(shoppingCartServiceImpl.reduceItem(userId, productId))
                .build();
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

}

