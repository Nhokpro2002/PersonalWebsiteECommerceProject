package com.newwave.bu3internecommerce.model.order;

public enum OrderStatus {
    PENDING, //The order has been placed but not yet processed.
    CONFIRMED, // The Order has been confirmed by the seller
    PROCESSING, // The order is being prepared
    SHIPPING, //
    DELIVERED, // Customer has received the order
    CANCELLED, // Customer has been canceled order
    RETURNED //Customer has returned order

}
