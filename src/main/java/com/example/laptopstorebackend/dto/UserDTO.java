package com.example.laptopstorebackend.dto;

import com.example.laptopstorebackend.constant.UserRole;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String userName;
    private String lastName;
    private String firstName;
    private String email;
    private String address;

    @JsonSerialize(using = ToStringSerializer.class)
    private UserRole role;
}
