package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.entity.ShoppingCart;
import com.example.laptopstorebackend.service.ShoppingCartServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController extends GenericController<ShoppingCart, Long> {

    public ShoppingCartController(ShoppingCartServiceImpl shoppingCartServiceImpl) {
        super(shoppingCartServiceImpl);
    }
}
