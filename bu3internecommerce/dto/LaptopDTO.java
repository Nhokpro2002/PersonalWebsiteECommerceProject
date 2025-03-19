package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.entity.Laptop;
import com.newwave.bu3internecommerce.entity.LaptopImage;

import java.util.List;

public record LaptopDTO(String name,
                        String category,
                        double sellingPrice,
                        List<String> images) {

    public static LaptopDTO mappingFromLaptop(Laptop laptop) {
        return new LaptopDTO(laptop.getName(),
                laptop.getCategory(),
                laptop.getSellingPrice(),
                convertLaptopImageToURL(laptop));
    }

    private static List<String> convertLaptopImageToURL(Laptop laptop) {
        return laptop.getImages().stream()
                .map(LaptopImage::getImageUrl).toList();

    }
}