package com.example.laptopstorebackend.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *
     * @param e
     * @param webRequest
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.setStatus(NOT_FOUND.value());
        errorResponse.setError(NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(e.getMessage());

        return errorResponse;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setStatus(INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.setMessage(e.getMessage());

        return errorResponse;
    }

    /**
     *
     * @param e
     * @param webRequest
     * @return
     */
    @ExceptionHandler(ArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleArgumentException(ArgumentException e, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.setStatus(BAD_REQUEST.value());
        errorResponse.setError(BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(e.getMessage());

        return errorResponse;
    }
}