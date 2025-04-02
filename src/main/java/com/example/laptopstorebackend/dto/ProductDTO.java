package com.example.laptopstorebackend.dto;

import com.example.laptopstorebackend.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String productName;
    private String description;
    private BigDecimal sellingPrice;
    private Category category;
    private String imageURL; // FIXME: product can many imageURL

}
