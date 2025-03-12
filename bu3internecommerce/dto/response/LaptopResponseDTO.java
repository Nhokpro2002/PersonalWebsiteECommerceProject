package com.newwave.bu3internecommerce.dto.response;

import com.newwave.bu3internecommerce.model.Laptop;

public record LaptopResponseDTO(String name,
                                String category,
                                double sellingPrice,
                                int stock) {

    public static LaptopResponseDTO fromEntity(Laptop laptop) {
        return new LaptopResponseDTO(laptop.getName(),
                laptop.getCategory(),
                laptop.getSellingPrice(),
                laptop.getStock());

    }
}