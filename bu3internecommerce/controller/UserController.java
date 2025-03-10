package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.request.UserCreationRequest;
import com.newwave.bu3internecommerce.dto.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.dto.response.ApiResponse;
import com.newwave.bu3internecommerce.dto.response.UserResponseDTO;
import com.newwave.bu3internecommerce.model.User;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import com.newwave.bu3internecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LaptopRepository laptopRepository;

    /**
     *
     * @param userCreationRequest
     * @return
     */
    @PostMapping()
    public ApiResponse<UserResponseDTO> createUser(
            @RequestBody @Valid UserCreationRequest userCreationRequest) {

        ApiResponse<UserResponseDTO> apiResponse =  new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationRequest));

        return apiResponse;
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/userId/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /**
     *
     * @param userId
     * @param userUpdateRequest
     * @return
     */
    @PutMapping("/userId/{userId}")
    UserResponseDTO updateUser(@PathVariable Long userId,
                    @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId, userUpdateRequest);

    }

    /**
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/userId/{userId}")
    String deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "delete User successfully";
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public Page findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
