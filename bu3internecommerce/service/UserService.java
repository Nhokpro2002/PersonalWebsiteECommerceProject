package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.mapper.UserMapper;
import com.newwave.bu3internecommerce.model.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.dto.UserDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.entity.user.User;
import com.newwave.bu3internecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return UserResponseDTO containing the user's details.
     * @throws AppException if the user does not exist.
     */
    public UserDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return UserMapper.mappingFromUser(user);
    }

    /**
     * Updates the details of an existing user.
     *
     * @param userId The ID of the user to update.
     * @param userUpdateRequest The request object containing updated user details.
     * @return UserResponseDTO containing the updated user's details.
     * @throws AppException if the user does not exist.
     */
    public UserDTO updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        existingUser.setEmail(userUpdateRequest.getEmail());
        existingUser.setPassword(userUpdateRequest.getPassword());
        existingUser.setUserName(userUpdateRequest.getUserName());
        userRepository.save(UserMapper.mappingFromUpdateRequest(userUpdateRequest));
        return UserMapper.mappingFromUser(existingUser);
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

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(user -> new UserDTO(
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()));
    }

}
