package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
