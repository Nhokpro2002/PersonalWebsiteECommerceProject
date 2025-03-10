package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.model.Laptop;
import lombok.Getter;

@Getter
public class LaptopDTO {
    private String name;
    private String category;
    private double sellingPrice;
    private int stock;


    public LaptopDTO(Laptop laptop) {
        this.name = laptop.getName();
        this.category = laptop.getCategory();
        this.sellingPrice = laptop.getSellingPrice();
        this.stock = laptop.getStock();

    }

}
