
package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.UserDTO;
import com.example.laptopstorebackend.dto.request.UserLoginRequest;
import com.example.laptopstorebackend.dto.request.UserRegisterRequest;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.service.implement.UserServiceImpl;
import com.example.laptopstorebackend.dto.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    public final UserServiceImpl userServiceImpl;

    /**
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ApiResponse.<UserDTO>builder()
                .code(200)
                .message("Register successfully")
                .data(userServiceImpl.register(userRegisterRequest))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<Jwt> login(@RequestBody UserLoginRequest userLoginRequest) {
        return ApiResponse.<Jwt>builder()
                .code(200)
                .message("Login successfully")
                .data(userServiceImpl.login(userLoginRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserDTO>> findAll() {
        return ApiResponse.<List<UserDTO>>builder()
                .code(200)
                .message("Get all users successfully")
                .data(userServiceImpl.findAllUser())
                .build();
    }
}

