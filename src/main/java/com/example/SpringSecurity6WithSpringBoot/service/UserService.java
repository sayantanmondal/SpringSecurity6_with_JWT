package com.example.SpringSecurity6WithSpringBoot.service;

import com.example.SpringSecurity6WithSpringBoot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    AuthenticationManager authManager;


    public String verifyUser(User user) {
        Authentication authentication  = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
            return "success";
        else return "failed";
    }
}
