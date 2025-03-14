package com.newwave.bu3internecommerce.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_EXISTED(409, "Username already exists. Please choose another one."),
    USER_NOT_EXISTED(404,"User Not Found"),
    CART_NOT_EXISTED(404,"Cart Not Found"),
    LAPTOP_NOT_EXISTED(404,"Laptop Not Found"),
    CART_ITEM_NOT_EXISTED(404,"Cart Item Not Found"),
    CART_EMPTY(404, "Cart Empty"),
    ORDERS_NOT_EXISTED(404, "Order Not Found"),
    PASSWORD_NOT_FOUND(404, "Password Not Found"),
    USERNAME_INVALID(400, "User Name must be at least 3 characters"),
    INVALID_PASSWORD(400, "Password must be at least 8 characters"),
    INVALID_KEY(400, "Invalid message key");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

}
