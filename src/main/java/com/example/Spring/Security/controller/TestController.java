package com.example.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/testing")
public class TestController {

    @GetMapping
    public String sayHello(){
        return "Welcome to our class";
    }

    @GetMapping("/calculate")
    public String calculate(){
        return String.valueOf(2 + 2);
    }
}
