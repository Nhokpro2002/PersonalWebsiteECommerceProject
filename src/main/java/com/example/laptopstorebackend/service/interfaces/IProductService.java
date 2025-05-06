package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.constant.Category;
import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;
import org.springframework.data.domain.Page;
import java.math.BigInteger;

public interface IProductService {

    ProductDTO findById(Long id);
    ProductDTO save(Product product);
    ProductDTO deleteById(Long id);
    ProductDTO updateSellingPrice(BigInteger sellingPrice, Long id);
    Page<ProductDTO> findAll(int page, int size );
    Integer countNumberItems();
    Integer countNumberItemsByCategory(Category category);
    void updateStock(Integer newStock, String productName);
    Page<ProductDTO> findByCategory(int page, int size, Category category);
    Page<ProductDTO> findByKeyword(String keyword, int page, int size);
}
