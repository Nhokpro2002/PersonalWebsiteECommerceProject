package com.example.laptopstorebackend.dto;

public class Jwt {
    private String token;

    public Jwt(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
