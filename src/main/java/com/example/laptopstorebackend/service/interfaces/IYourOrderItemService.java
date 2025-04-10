package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.dto.YourOrderItemDTO;

import java.util.List;

public interface IYourOrderItemService {
    List<YourOrderItemDTO> findAll(Long yourOrderId);
    List<YourOrderItemDTO> createOrderItem(Long shoppingCartId, Long yourOrderId);
}
