package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(Product product);
    void deleteById(Long id);
    ProductDTO update(BigDecimal sellingPrice, Long id);
}
