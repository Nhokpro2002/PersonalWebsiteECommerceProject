package com.newwave.bu3internecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UserCreationRequest {

    @Size(min = 3, message = "UserName must be at least 3 characters")
    private String userName;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate localDate;



}
