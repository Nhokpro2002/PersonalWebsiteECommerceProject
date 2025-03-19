package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.entity.user.User;
import com.newwave.bu3internecommerce.mapper.UserMapper;
import com.newwave.bu3internecommerce.model.request.UserLoginRequest;
import com.newwave.bu3internecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import com.newwave.bu3internecommerce.dto.UserDTO;
import com.newwave.bu3internecommerce.model.request.UserRegisterRequest;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final CartService cartService;

    /**
     * Register the new User
     * @param userRegisterRequest Object consist of information of new User
     * @return UserDTO
     */
    public UserDTO register(UserRegisterRequest userRegisterRequest) {
        Optional<User> existingUser = userRepository.findByUserName(userRegisterRequest.getUserName());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists!");
        }
        User newUser = UserMapper.mappingFromRegisterRequest((userRegisterRequest));
        newUser.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        userRepository.save(newUser);
        cartService.createCart(newUser); // create Shopping Cart for new User
        return UserMapper.mappingFromUser(newUser);
    }

    /**
     * User login
     * @param userLoginRequest Object consist of information login: username and password
     * @return Token String if login successfully
     */
    public String login(UserLoginRequest userLoginRequest) {
        User exsitingUser = userRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if (passwordEncoder.matches(userLoginRequest.getPassword(), exsitingUser.getPassword())) {
            boolean match = passwordEncoder.matches(userLoginRequest.getPassword(), exsitingUser.getPassword());
            return generateToken(exsitingUser);
        }
        return "Incorrect password";
    }

    /**
     * Create token (JWT) consist of user information
     * @param user The User
     * @return Result success/fail
     */
    private String generateToken(User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:8080")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(user.getUserName())
                .claim("Role", user.getRole())
                .build();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims));
        return jwt.getTokenValue();
    }

}