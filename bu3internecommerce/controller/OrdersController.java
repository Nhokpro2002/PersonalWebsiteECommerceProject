package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.response.ApiResponse;
import com.newwave.bu3internecommerce.dto.response.OrdersDTO;
import com.newwave.bu3internecommerce.service.OrdersService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/cartId/{cartId}")
    public ApiResponse<OrdersDTO> placeOrder(@PathVariable Long cartId) {
        ApiResponse<OrdersDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ordersService.placeOrder(cartId));
        return apiResponse;
    }


}
