package com.newwave.bu3internecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newwave.bu3internecommerce.model.User;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String userName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public User createUser(UserCreationRequest userCreationRequest) {
        return User.builder()
                .localDate(userCreationRequest.getLocalDate())
                .userName(userCreationRequest.getUserName())
                .password(userCreationRequest.getPassword())
                .firstName(userCreationRequest.getFirstName())
                .lastName(userCreationRequest.getLastName())
                .build();
    }

}
