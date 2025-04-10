package com.example.laptopstorebackend.dto.request;

import com.example.laptopstorebackend.constant.UserRole;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String lastName;
    private String address;
    private String firstName;
    private String userName;
    private String userPassword;
    private UserRole userRole;

}
