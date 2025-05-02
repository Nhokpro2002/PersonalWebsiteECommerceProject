package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import com.example.laptopstorebackend.dto.YourOrderDTO;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.service.implement.JwtServiceImpl;
import com.example.laptopstorebackend.service.implement.YourOrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("your-orders")
public class YourOrderController {

    private final YourOrderServiceImpl yourOrderServiceImpl;
    private final JwtServiceImpl jwtServiceImpl;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping
    public ApiResponse<YourOrderDTO> createYourOrder(/*@RequestParam Long shoppingCartId*/HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        Long userId = jwtServiceImpl.extractUserId(token);
        // UserId == ShoppingCartId
        return ApiResponse.<YourOrderDTO>builder()
                .code(200)
                .message("Create your order successfully")
                .data(yourOrderServiceImpl.createYourOrder(userId))
                .build();
    }

    /**
     *
     * @param yourOrderId
     * @param newStatus
     * @return
     */
    @PatchMapping
    public ApiResponse<YourOrderDTO> changeOrderStatus(@RequestParam Long yourOrderId,
                                                       @RequestParam YourOrderStatus newStatus) {
        return ApiResponse.<YourOrderDTO>builder()
                .code(200)
                .message("Change Order Status successfully")
                .data(yourOrderServiceImpl.changeYourOrderStatus(yourOrderId, newStatus))
                .build();
    }

    /**
     *
     * @param yourOrderId
     * @return
     */
    @GetMapping
    public ApiResponse<YourOrderDTO> getOrder(@RequestParam Long yourOrderId) {
        return ApiResponse.<YourOrderDTO>builder()
                .code(200)
                .message("Your Order: ")
                .data(yourOrderServiceImpl.getYourOrder(yourOrderId))
                .build();
    }

    @GetMapping("/size")
    public ApiResponse<Integer> countOrderNumber() {
        return ApiResponse.<Integer>builder()
                .code(200)
                .message("Order quantity: ")
                .data(yourOrderServiceImpl.countOrderNumber())
                .build();
    }

    /**
     *
     * @param request
     * @return
     */
    private String extractTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
