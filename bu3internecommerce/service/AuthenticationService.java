package com.newwave.bu3internecommerce.service;


import com.newwave.bu3internecommerce.dto.request.AuthenticationRequest;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.model.User;
import com.newwave.bu3internecommerce.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Verify request of user
     * @param authenticationRequest The Request of User
     * @return result of request: Success/Fail
     */
    public boolean authenticate(AuthenticationRequest authenticationRequest) {
        System.out.println("Password from Request: " + authenticationRequest.getPassword());
        User user = userRepository.findByUserName(authenticationRequest.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    }
}
