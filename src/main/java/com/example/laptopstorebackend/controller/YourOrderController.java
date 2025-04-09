package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import com.example.laptopstorebackend.dto.YourOrderDTO;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.service.implement.YourOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("your-orders")
public class YourOrderController {

    private final YourOrderServiceImpl yourOrderServiceImpl;

    @PostMapping
    public ApiResponse<YourOrderDTO> createYourOrder(@RequestParam Long shoppingCartId) {
        return ApiResponse.<YourOrderDTO>builder()
                .code(200)
                .message("Create your order successfully")
                .data(yourOrderServiceImpl.createYourOrder(shoppingCartId))
                .build();
    }

    @PatchMapping
    public ApiResponse<YourOrderDTO> changeOrderStatus(@RequestParam Long yourOrderId,
                                                       @RequestParam YourOrderStatus newStatus) {
        return ApiResponse.<YourOrderDTO>builder()
                .code(200)
                .message("Change Order Status successfully")
                .data(yourOrderServiceImpl.changeYourOrderStatus(yourOrderId, newStatus))
                .build();
    }

    @GetMapping
    public ApiResponse<YourOrderDTO> getOrder(@RequestParam Long yourOrderId) {
        return ApiResponse.<YourOrderDTO>builder()
                .code(200)
                .message("Your Order: ")
                .data(yourOrderServiceImpl.getYourOrder(yourOrderId))
                .build();
    }
}
