package com.example.SpringSecurity6WithSpringBoot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @GetMapping("/{id}")
    public String getproductById(@PathVariable Long id){

        return "product_for_"+id;

    }



}
