package com.example.SpringSecurity6WithSpringBoot.controller;

import com.example.SpringSecurity6WithSpringBoot.repository.UserRepository;
import com.example.SpringSecurity6WithSpringBoot.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MyUserDetailsService userService;

    @GetMapping
    public String greet(HttpServletRequest request){
        return "Welcome" + request.getSession().getId();
    }

    @GetMapping("/home")
    public ResponseEntity<?> home(){
          return ResponseEntity.ok("welcome home");
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveSomeData(@RequestBody String data){
        return ResponseEntity.ok("data saved successfully");
        }



}
