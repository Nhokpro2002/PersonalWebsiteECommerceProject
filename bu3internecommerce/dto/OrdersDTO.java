package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.entity.order.OrderStatus;
import com.newwave.bu3internecommerce.entity.order.Orders;
import com.newwave.bu3internecommerce.entity.order.OrdersItem;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record OrdersDTO (Long ordersId,
                        LocalDate ordersDate,
                        OrderStatus orderStatus,
                        List<OrdersItemDTO> ordersItemDTO,
                        double ordersTotalPrice) {

    public static OrdersDTO fromEntity(Orders orders) {
        return new OrdersDTO(orders.getId(),
                orders.getOrderDate(),
                orders.getOrderStatus(),
                convertFromCartItemToDTO(orders.getOrderItems()),
                calculusTotalPrice(orders.getOrderItems()));
    }

    private static List<OrdersItemDTO> convertFromCartItemToDTO(List<OrdersItem> ordersItems) {
        return ordersItems.stream()
                .map(OrdersItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    private static double calculusTotalPrice(List<OrdersItem> ordersItems) {
        return ordersItems.stream()
                .mapToDouble(ordersItem -> ordersItem.getLaptop().getSellingPrice() * ordersItem.getQuantity())
                .sum();
    }
}
