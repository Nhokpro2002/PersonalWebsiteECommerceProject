package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import com.example.laptopstorebackend.dto.YourOrderDTO;

public interface IYourOrderService {

    /**
     * Create yourOrder from Shopping Cart
     * @param shoppingCartId Your Shopping Cart Id
     * @return YourOrderDTO: list of product and totalPrice
     */
    YourOrderDTO createYourOrder(Long shoppingCartId);

    YourOrderDTO changeYourOrderStatus(Long yourOrderId, YourOrderStatus newStatus);

    YourOrderDTO getYourOrder(Long yourOrderId);

    Integer countOrderNumber();
}
