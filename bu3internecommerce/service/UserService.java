package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.request.UserCreationRequest;
import com.newwave.bu3internecommerce.dto.request.UserUpdateRequest;
import com.newwave.bu3internecommerce.model.User;
import com.newwave.bu3internecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(UserCreationRequest userCreationRequest) {
        User user = new User();

        if (userRepository.existsByUserName(userCreationRequest.getUserName()))
            throw new RuntimeException("User exits");


        user.setUserName(userCreationRequest.getUserName());
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setPassword(userCreationRequest.getPassword());
        user.setLocalDate(userCreationRequest.getLocalDate());

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = getUser(userId);

        user.setUserName(userUpdateRequest.getUserName());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setPassword(userUpdateRequest.getPassword());
        user.setLocalDate(userUpdateRequest.getLocalDate());

        return userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }


}
