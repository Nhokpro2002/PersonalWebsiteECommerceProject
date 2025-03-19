package com.newwave.bu3internecommerce.mapper;

import com.newwave.bu3internecommerce.dto.UserDTO;
import com.newwave.bu3internecommerce.entity.user.User;
import com.newwave.bu3internecommerce.model.request.UserRegisterRequest;
import com.newwave.bu3internecommerce.model.request.UserUpdateRequest;

public class UserMapper {

    public static User mappingFromRegisterRequest(UserRegisterRequest userRegisterRequest) {
        return User.builder()
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .userName(userRegisterRequest.getUserName())
                .password(userRegisterRequest.getPassword())
                .email(userRegisterRequest.getEmail())
                .role(userRegisterRequest.getRole())
                .localDate(userRegisterRequest.getLocalDate())
                .build();

    }

    public static User mappingFromUpdateRequest(UserUpdateRequest userUpdateRequest) {
        return User.builder()
                .firstName(userUpdateRequest.getFirstName())
                .lastName(userUpdateRequest.getLastName())
                .userName(userUpdateRequest.getUserName())
                .password(userUpdateRequest.getPassword())
                .email(userUpdateRequest.getEmail())
                .localDate(userUpdateRequest.getLocalDate())
                .build();
    }

    public static UserDTO mappingFromUser(User user) {
        return new UserDTO(user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
