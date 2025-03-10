package com.newwave.bu3internecommerce.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "laptop")
@Getter
@Setter
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private double sellingPrice;

    @Column(name = "stock")
    private int stock;

    @Column(name = "import_price")
    private double importPrice;

}

