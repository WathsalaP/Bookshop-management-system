package com.pahana_edu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pahana_edu.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getuser")
    public String getUser() {
        return userService.getAllUsers().toString();
    }
}
