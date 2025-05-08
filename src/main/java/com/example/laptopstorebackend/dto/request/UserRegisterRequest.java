package com.example.laptopstorebackend.dto.request;

import com.example.laptopstorebackend.constant.UserRole;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank(message = "Email cannot have spaces")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name cannot have spaces")
    private String lastName;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name cannot have spaces")
    private String firstName;

    @NotEmpty(message = "User name is required")
    @NotBlank(message = "User name cannot have spaces")
    @Size(min = 5, message = "User name must have at least 5 characters")
    private String userName;

    @NotEmpty(message = "Password not empty")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain letters, numbers, and at least one special character"
    )
    private String userPassword;

    //@NotNull(message = "User role is required")
    //private UserRole userRole;

}
