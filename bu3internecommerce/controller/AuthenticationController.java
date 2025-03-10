package com.newwave.bu3internecommerce.controller;

import com.newwave.bu3internecommerce.dto.request.AuthenticationRequest;
import com.newwave.bu3internecommerce.dto.response.ApiResponse;
import com.newwave.bu3internecommerce.dto.response.AuthenticationResponse;
import com.newwave.bu3internecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
        public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
           boolean result =  authenticationService.authenticate(authenticationRequest);
           return ApiResponse.<AuthenticationResponse>builder()
                   .result(AuthenticationResponse.builder()
                           .authenticated(result)
                           .build())
                   .build();
        }


}
