package com.newwave.bu3internecommerce.dto.response;

import com.newwave.bu3internecommerce.model.order.OrderItem;
import com.newwave.bu3internecommerce.model.order.OrderStatus;
import com.newwave.bu3internecommerce.model.order.Orders;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrdersDTO {

    private Long id;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;
    private double price;

    public OrdersDTO(Orders orders) {
        this.orderDate = orders.getOrderDate();
        this.orderStatus = orders.getOrderStatus();
        this.orderItems = orders.getOrderItems();
        this.price = orders.getPrice();

    }
}
