package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.entity.User;
import com.example.laptopstorebackend.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<User, Long>{

    public UserController(UserServiceImpl userServiceImpl) {
        super(userServiceImpl);
    }

}
