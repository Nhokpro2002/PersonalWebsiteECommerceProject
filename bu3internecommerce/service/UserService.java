package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.request.UserCreationRequest;
import com.newwave.bu3internecommerce.dto.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.dto.response.UserResponseDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.model.User;
import com.newwave.bu3internecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    /**
     *
     * @param userCreationRequest
     * @return
     */
    public UserResponseDTO createUser(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByUserName(userCreationRequest.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = User.builder()
                .userName(userCreationRequest.getUserName())
                .firstName(userCreationRequest.getFirstName())
                .lastName(userCreationRequest.getLastName())
                .password(userCreationRequest.getPassword())
                .localDate(userCreationRequest.getLocalDate())
                .build();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));


        UserResponseDTO userResponseDTO = UserResponseDTO.fromEntity(user);
        userRepository.save(user);
        return userResponseDTO;
    }

    /**
     *
     * @param userId
     * @return
     */
    public User getUser(Long userId) {
        return  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     *
     * @param userId
     * @param userUpdateRequest
     * @return
     */
    public UserResponseDTO updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = getUser(userId);

        user.setUserName(userUpdateRequest.getUserName());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setPassword(userUpdateRequest.getPassword());
        user.setLocalDate(userUpdateRequest.getLocalDate());

        userRepository.save(user);

        return UserResponseDTO.fromEntity(user);
    }

    /**
     *
     * @param userId
     */
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponseDTO::fromEntity);
    }


}
