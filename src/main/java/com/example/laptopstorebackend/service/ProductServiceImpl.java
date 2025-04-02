package com.example.laptopstorebackend.service;

import com.example.laptopstorebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> {

    public ProductServiceImpl(JpaRepository<Product, Long> repository) {
        super(repository);
    }
}
