package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.UserDTO;
import com.newwave.bu3internecommerce.model.request.UserLoginRequest;
import com.newwave.bu3internecommerce.model.request.UserRegisterRequest;
import com.newwave.bu3internecommerce.model.response.ApiResponse;
import com.newwave.bu3internecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ApiResponse<UserDTO> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            UserDTO userDTO = authenticationService.register(userRegisterRequest);
            return new ApiResponse<>(200, "User registered successfully", userDTO);
        } catch (Exception e) {
            return new ApiResponse<>(500, "Registration failed: " + e.getMessage(), null);
        }
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody UserLoginRequest userLoginRequest) {

            return new ApiResponse<>(200, "User Login successfully", authenticationService.login(userLoginRequest));
    }

}
