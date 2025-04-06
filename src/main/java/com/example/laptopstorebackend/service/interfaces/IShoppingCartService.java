package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.dto.ShoppingCartDTO;

public interface IShoppingCartService {

    ShoppingCartDTO getAllItem(Long shoppingCartId);

    ShoppingCartDTO addItem(Long shoppingCartId, Long productId);

    ShoppingCartDTO reduceItem(Long shoppingCartId, Long productId);
}
