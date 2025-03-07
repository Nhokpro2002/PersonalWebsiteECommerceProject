package com.newwave.bu3internecommerce.exception;


import com.newwave.bu3internecommerce.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRunTimeException(
            RuntimeException runtimeException) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(1000);
        apiResponse.setMessage(runtimeException.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value  = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingValidation(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest().body(Objects.requireNonNull(methodArgumentNotValidException
                        .getFieldError())
                .getDefaultMessage());
    }
}
