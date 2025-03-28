package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
