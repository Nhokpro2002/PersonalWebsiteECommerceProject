package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.model.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.model.response.ApiResponse;
import com.newwave.bu3internecommerce.dto.UserDTO;
import com.newwave.bu3internecommerce.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get User by userId
     * @param userId The userId of User
     * @return UserResponseDTO contain information User details
     */
    @GetMapping("/userId/{userId}")
    public ApiResponse<UserDTO> getUser(@PathVariable Long userId) {
        ApiResponse<UserDTO> apiResponse =  new ApiResponse<>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    /**
     * update information for User
     * @param userId The userId of User
     * @param userUpdateRequest The request update User contain: firstName, lastName, password, ...
     * @return UserResponseDTO contain information User details
     */
    @PutMapping("/userId/{userId}")
    public ApiResponse<UserDTO> updateUser(@PathVariable Long userId,
                                           @RequestBody UserUpdateRequest userUpdateRequest) {
        ApiResponse<UserDTO> apiResponse =  new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, userUpdateRequest));
        return apiResponse;
    }

    /**
     * Delete the User by userId
     * @param userId The userId of User
     * @return result delete User
     */
    @DeleteMapping("/userId/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        ApiResponse<String> apiResponse =  new ApiResponse<>();
        apiResponse.setMessage("Delete user successfuly");
        return apiResponse;
    }

    /**
     * Retrieves a paginated list of all users.
     *
     * @param pageable Pagination details including page number and size.
     * @return A paginated list of UserResponseDTO objects.
     */
    @GetMapping
    public Page<UserDTO> findAll(Pageable pageable) {

        return userService.findAll(pageable);
    }
}
