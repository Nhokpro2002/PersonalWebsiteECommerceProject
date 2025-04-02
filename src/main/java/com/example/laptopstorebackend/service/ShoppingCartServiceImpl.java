package com.example.laptopstorebackend.service;

import com.example.laptopstorebackend.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends GenericServiceImpl<ShoppingCart, Long> {

    public ShoppingCartServiceImpl(JpaRepository<ShoppingCart, Long> repository) {
        super(repository);
    }
}
