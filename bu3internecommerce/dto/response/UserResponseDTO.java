package com.newwave.bu3internecommerce.dto.response;

import com.newwave.bu3internecommerce.model.User;

public record UserResponseDTO(String username,
                              String firstName,
                              String lastName) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(user.getUserName(), 
                                   user.getFirstName(),
                                   user.getLastName());

    }
}
