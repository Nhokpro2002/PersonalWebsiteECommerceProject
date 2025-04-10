
package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.constant.UserRole;
import com.example.laptopstorebackend.dto.UserDTO;
import com.example.laptopstorebackend.dto.request.UserLoginRequest;
import com.example.laptopstorebackend.dto.request.UserRegisterRequest;
import com.example.laptopstorebackend.entity.User;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.UserRepository;
import com.example.laptopstorebackend.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param userId
     * @return
     */
    public UserDTO findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return convertFromEntity(user.get());
        }
        throw new ResourceNotFoundException("User not found");
    }

    /**
     *
     * @param userRegisterRequest
     * @return
     */
    public UserDTO register(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .userName(userRegisterRequest.getUserName())
                .userPassword(passwordEncoder.encode(userRegisterRequest.getUserPassword()))
                .email(userRegisterRequest.getEmail())
                .address(userRegisterRequest.getAddress())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .userRole(UserRole.CUSTOMER)
                .build();
        userRepository.save(user);
        return convertFromEntity(user);
    }

    /**
     *
     * @param userLoginRequest
     */
    public Jwt login(UserLoginRequest userLoginRequest) {

    }

    public UserDTO convertFromEntity(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .address(user.getAddress())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .role(user.getUserRole())
                .build();
    }

}
