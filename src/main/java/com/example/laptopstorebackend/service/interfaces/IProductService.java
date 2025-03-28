package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.entity.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long productId);
    Product saveProduct(Product product);
    void deleteProduct(Long productId);
    Product updateProduct(Product product);
}
