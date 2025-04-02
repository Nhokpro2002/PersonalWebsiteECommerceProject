package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GenericRepository<Product, Long> {
}
