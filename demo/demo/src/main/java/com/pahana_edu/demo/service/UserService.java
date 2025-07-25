package com.pahana_edu.demo.service;

import java.util.List;

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
}
