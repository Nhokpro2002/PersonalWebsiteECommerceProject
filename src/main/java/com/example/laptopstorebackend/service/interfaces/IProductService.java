package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(Product product);
    ProductDTO deleteById(Long id);
    ProductDTO updateSellingPrice(BigDecimal sellingPrice, Long id);

    void updateStock(Integer newStock, String productName);
}
