package com.example.laptopstorebackend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArgumentException extends RuntimeException {
    public ArgumentException(String message) {
        super(message);
    }
}
