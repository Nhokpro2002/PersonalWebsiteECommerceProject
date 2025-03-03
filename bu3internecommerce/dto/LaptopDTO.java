package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.model.Laptop;

public class LaptopDTO {
    private String name;
    private String category;
    private double price;


    public LaptopDTO(Laptop laptop) {
        this.name = laptop.getName();
        this.category = laptop.getCategory();
        this.price = laptop.getPrice();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
