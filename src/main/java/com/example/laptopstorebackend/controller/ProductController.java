package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends GenericController<Product, Long> {

    public ProductController(ProductServiceImpl productServiceImpl) {
        super(productServiceImpl);
    }
}
