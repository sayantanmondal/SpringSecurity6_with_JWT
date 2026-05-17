package com.example.SpringSecurity6WithSpringBoot.controller;

import com.example.SpringSecurity6WithSpringBoot.entities.User;
import com.example.SpringSecurity6WithSpringBoot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    UserRepository userRepository;

    @GetMapping("register")
    public String registerUser(@RequestBody User user){
        userRepository.save(user);

        return "true";

    }



}
