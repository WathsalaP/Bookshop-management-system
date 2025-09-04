package com.pahana_edu.demo.service;

import com.pahana_edu.demo.dto.UserDTO;
import com.pahana_edu.demo.model.UserModel;
import com.pahana_edu.demo.repo.UserRepo;
//import com.pahana_edu.demo.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    private UserModel userModel;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userModel = new UserModel("U1", "John", "john@example.com", "12345", "pass"); // --> New
        userDTO = new UserDTO("U1", "John", "john@example.com", "12345", "pass"); // --> New
    }

    @Test
    void testGetAllUsers() {
        when(userRepo.findAll()).thenReturn(Arrays.asList(userModel));
        when(modelMapper.map(anyList(), any(java.lang.reflect.Type.class)))
                .thenReturn(Arrays.asList(userDTO));

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getUsername());
    }

    @Test
    void testSaveUser() {
        when(modelMapper.map(userDTO, UserModel.class)).thenReturn(userModel);
        when(userRepo.save(any(UserModel.class))).thenReturn(userModel);

        UserDTO result = userService.saveUser(userDTO);

        assertEquals("John", result.getUsername());
        verify(userRepo, times(1)).save(userModel);
    }

    @Test
    void testUpdateUser_Success() {
        when(userRepo.findById("U1")).thenReturn(Optional.of(userModel));
        when(userRepo.save(any(UserModel.class))).thenReturn(userModel);

        UserDTO result = userService.updateUser(userDTO);

        assertEquals("John", result.getUsername());
        verify(userRepo, times(1)).save(userModel);
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepo.findById("U2")).thenReturn(Optional.empty());

        userDTO.setUserid("U2");
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.updateUser(userDTO));
        assertTrue(ex.getMessage().contains("User not found"));
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepo.findById("U1")).thenReturn(Optional.of(userModel));

        userService.deleteUser("U1");

        verify(userRepo, times(1)).delete(userModel);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepo.findById("U2")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.deleteUser("U2"));
        assertTrue(ex.getMessage().contains("User not found"));
    }
}
