package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.entity.order.OrdersItem;

public record OrdersItemDTO(LaptopDTO laptopDTO,
                            int quantity,
                            double totalPrice) {

    public static OrdersItemDTO fromEntity(OrdersItem ordersItem) {
        return new OrdersItemDTO(LaptopDTO.mappingFromLaptop(ordersItem.getLaptop()),
                ordersItem.getQuantity(),
                ordersItem.getLaptop().getSellingPrice() * ordersItem.getQuantity());
    }
}
