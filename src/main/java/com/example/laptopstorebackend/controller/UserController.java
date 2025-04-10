
package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.UserDTO;
import com.example.laptopstorebackend.dto.request.UserRegisterRequest;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.service.implement.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}

