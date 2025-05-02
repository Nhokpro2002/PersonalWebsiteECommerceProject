package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.entity.CustomUserDetails;
import com.example.laptopstorebackend.entity.User;
import com.example.laptopstorebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
                user.getId(),
                user.getUserName(),
                user.getUserPassword(),
                List.of(() -> "ROLE_" + user.getUserRole())
        );
    }

}
