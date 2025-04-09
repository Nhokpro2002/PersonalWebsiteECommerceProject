package com.example.laptopstorebackend.dto;

import com.example.laptopstorebackend.constant.Category;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal sellingPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    private Category category;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer stock;
    private String imageUrl; // FIXME: product can many imageURL

}
