package com.example.laptopstorebackend.dto.request;

import com.example.laptopstorebackend.constant.UserRole;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;

    @NotEmpty(message = "Field last name can't empty")
    @NotBlank(message = "Field last name can't blank")
    private String lastName;

    @NotEmpty(message = "Field address can't empty")
    private String address;

    @NotEmpty(message = "Field first name can't empty")
    @NotBlank(message = "Field first name can't blank")
    private String firstName;

    @NotEmpty(message = "Field user name can't empty")
    @NotBlank(message = "Field user name can't blank")
    @Size(min = 5, message = "User name must have at least 5 characters")
    private String userName;

    @NotEmpty(message = "Field user password can't empty")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "Password must contain both letters and numbers"
    )
    private String userPassword;

    @NotNull(message = "User role is required")
    private UserRole userRole = UserRole.CUSTOMER;

}
