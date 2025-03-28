package com.example.laptopstorebackend.entity;

import com.example.laptopstorebackend.constant.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "import_price")
    private BigDecimal importingPrice;

    @Column(name = "sell_price")
    private BigDecimal sellingPrice;

    @Column(name = "product_stock")
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "image_URL")
    private String imageURL;
}
