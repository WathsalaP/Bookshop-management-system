package com.pahana_edu.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pahana_edu.demo.dto.UserDTO;
import com.pahana_edu.demo.model.UserModel;
import com.pahana_edu.demo.repo.UserRepo;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<UserModel> usersList = userRepo.findAll();
        return modelMapper.map(usersList, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    public UserDTO saveUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, UserModel.class));
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        Optional<UserModel> optionalUser = userRepo.findById(userDTO.getUserid());

        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();

            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPhone_number(userDTO.getPhone_number());
            existingUser.setPassword(userDTO.getPassword());

            userRepo.save(existingUser);
            return userDTO;
        } else {
            throw new RuntimeException("User not found with ID: " + userDTO.getUserid());
        }
    }

    public void deleteUser(String userid) {
        Optional<UserModel> optionalUser = userRepo.findById(userid);
        if (optionalUser.isPresent()) {
            userRepo.delete(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with ID: " + userid);
        }
    }

}
