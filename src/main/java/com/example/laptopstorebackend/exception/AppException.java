package com.example.laptopstorebackend.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException {
    private int code;
    private String message;
}
