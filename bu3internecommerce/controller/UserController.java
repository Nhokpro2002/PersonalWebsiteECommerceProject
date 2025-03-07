package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.request.UserCreationRequest;
import com.newwave.bu3internecommerce.dto.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.dto.response.ApiResponse;
import com.newwave.bu3internecommerce.model.User;
import com.newwave.bu3internecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ApiResponse<User> createUser(
            @RequestBody @Valid UserCreationRequest userCreationRequest) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationRequest));
        return apiResponse;
    }

    @GetMapping
    List<User> getUser() {
        return userService.findAll();
    }

    @GetMapping("/userId/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/userId/{userId}")
    User updateUser(@PathVariable Long userId,
                    @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId, userUpdateRequest);

    }

    @DeleteMapping("/userId/{userId}")
    String deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "delete User successfully";
    }
}
