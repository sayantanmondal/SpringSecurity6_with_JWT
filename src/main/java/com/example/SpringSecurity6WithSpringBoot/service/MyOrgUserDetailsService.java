package com.example.SpringSecurity6WithSpringBoot.service;

import com.example.SpringSecurity6WithSpringBoot.entities.User;
import com.example.SpringSecurity6WithSpringBoot.model.UserPrincipal;
import com.example.SpringSecurity6WithSpringBoot.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MyOrgUserDetailsService implements UserDetailsService {
//    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("user not found");
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
