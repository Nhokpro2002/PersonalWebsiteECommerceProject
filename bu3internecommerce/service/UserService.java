package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.request.UserCreationRequest;
import com.newwave.bu3internecommerce.dto.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.dto.response.UserResponseDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.model.User;
import com.newwave.bu3internecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user and returns the user details.
     *
     * @param userCreationRequest The request object containing user details such as first name, last name, username, and password.
     * @return UserResponseDTO containing the details of the newly created user.
     * @throws AppException if the username already exists in the system.
     */
    public UserResponseDTO createUser(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByUserName(userCreationRequest.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userCreationRequest.createUser(userCreationRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));

        UserResponseDTO userResponseDTO = UserResponseDTO.fromEntity(user);
        userRepository.save(user);
        return userResponseDTO;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return UserResponseDTO containing the user's details.
     * @throws AppException if the user does not exist.
     */
    public UserResponseDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return UserResponseDTO.fromEntity(user);
    }

    /**
     * Updates the details of an existing user.
     *
     * @param userId The ID of the user to update.
     * @param userUpdateRequest The request object containing updated user details.
     * @return UserResponseDTO containing the updated user's details.
     * @throws AppException if the user does not exist.
     */
    public UserResponseDTO updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.updateFrom(userUpdateRequest);
        userRepository.save(user);
        return UserResponseDTO.fromEntity(user);
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param userId The ID of the user to delete.
     */
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Retrieves a paginated list of all users.
     *
     * @param pageable Pagination details including page number and size.
     * @return A paginated list of UserResponseDTO objects.
     */
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponseDTO::fromEntity);
    }
}
