package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.ShoppingCart;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends GenericRepository<ShoppingCart, Long> {
}
