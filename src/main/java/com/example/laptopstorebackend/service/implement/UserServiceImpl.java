
package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.constant.UserRole;
import com.example.laptopstorebackend.dto.UserDTO;
import com.example.laptopstorebackend.dto.request.UserLoginRequest;
import com.example.laptopstorebackend.dto.request.UserRegisterRequest;
import com.example.laptopstorebackend.entity.CustomUserDetails;
import com.example.laptopstorebackend.entity.User;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.UserRepository;
import com.example.laptopstorebackend.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.laptopstorebackend.dto.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtServiceImpl;
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;

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
     * @param userId
     * @param newAddress
     * @return
     */
    public UserDTO updateUserInfo(Long userId, String newAddress) {

        /*User user = userRepository.findById(userId).orElseThrow();
        user.setAddress(newAddress);
        userRepository.save(user);
        return convertFromEntity(user);*/
        Optional<User> user = userRepository.findById(userId);
        if (newAddress.isEmpty() || user.isEmpty()) {
            throw new RuntimeException("Error and Nhokpro2002 wouldn't fix the error, OK!");
        }
        User realUser = user.get();
        realUser.setAddress(newAddress);
        userRepository.save(realUser);
        return convertFromEntity(realUser);
    }

    /**
     *
     * @return
     */
    public List<UserDTO> findAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
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
        user = userRepository.save(user);
        // user register successfully => create Shopping Cart for user (userId == shoppingCartId)
        shoppingCartServiceImpl.createShoppingCart(user.getId());

        return convertFromEntity(user);
    }

    /**
     *
     * @param userLoginRequest
     */
    public Jwt login(UserLoginRequest userLoginRequest) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.getUserName(),
                userLoginRequest.getUserPassword()));
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = customUserDetails.getUserId();
        String token = jwtServiceImpl.generateToken(auth.getName(), userId, customUserDetails.getAuthorities());
        return new Jwt(token);
    }

    /**
     *
     * @param user
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public Integer countUserNumber() {
        return (int) userRepository.count();
    }
}
