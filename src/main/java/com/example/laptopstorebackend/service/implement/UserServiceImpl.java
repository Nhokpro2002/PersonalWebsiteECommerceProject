
package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.UserDTO;
import com.example.laptopstorebackend.entity.User;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.UserRepository;
import com.example.laptopstorebackend.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserDTO findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return convertFromEntity(user.get());
        }
        throw new ResourceNotFoundException("User not found");
    }

    public UserDTO convertFromEntity(User user) {
        return UserDTO.builder()
                .address(user.getAddress())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .role(user.getUserRole())
                .build();
    }

}
