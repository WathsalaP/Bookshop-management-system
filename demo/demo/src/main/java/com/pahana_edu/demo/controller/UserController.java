package com.pahana_edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pahana_edu.demo.dto.UserDTO;
import com.pahana_edu.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public String saveUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "User added successfully!";
    }

    @PatchMapping("/updateuser/{id}")
    public String updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        userDTO.setUserID(id); // set userID from path variable
        userService.updateUser(userDTO);
        return "User updated successfully!";
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }

}
