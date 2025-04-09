package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Object> findByProductName(String productName);
}
