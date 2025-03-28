package com.example.laptopstorebackend.mapper;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;

public class ProductConverter {

    public static ProductDTO covertFromEntity(Product product) {
        return ProductDTO.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .sellingPrice(product.getSellingPrice())
                .imageURL(product.getImageURL())
                .build();
    }

}
