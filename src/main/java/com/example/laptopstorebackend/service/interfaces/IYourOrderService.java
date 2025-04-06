package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.dto.YourOrderDTO;

public interface IYourOrderService {

    /**
     * Create yourOrder from Shopping Cart
     * @param shoppingCartId Your Shopping Cart Id
     * @return YourOrderDTO: list of product and totalPrice
     */
    YourOrderDTO createYourOrder(Long shoppingCartId);
}
