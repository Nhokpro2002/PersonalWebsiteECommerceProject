
package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.FacebookUserInfo;
import com.example.laptopstorebackend.dto.Jwt;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.service.implement.FacebookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("facebook")
public class FacebookLoginController {

    private final FacebookServiceImpl facebookServiceImpl;

    @GetMapping("/oauth")
    public ApiResponse<String> getUrlOauthFacebook() {
        return ApiResponse.<String>builder()
                .code(200)
                .message("Login facebook")
                .data(facebookServiceImpl.loginByFacebook())
                .build();

    }

    @GetMapping("/token")
    public ApiResponse<String> getToken(@RequestParam String code) {
        return ApiResponse.<String>builder()
                .code(200)
                .message("change code -> token success")
                .data(facebookServiceImpl.getFacebookAccessToken(code))
                .build();
    }


 @GetMapping("/userInfo")
    public ApiResponse<FacebookUserInfo> getUserInfo(@RequestParam String token) {
        return ApiResponse.<FacebookUserInfo>builder()
                .code(200)
                .message("change code -> token success")
                .data(facebookServiceImpl.getFacebookUser(token))
                .build();
    }


}


