package com.example.laptopstorebackend.dto.request;


import lombok.Data;

@Data
public class UserLoginRequest {
    private String userName;
    private String userPassword;
}
