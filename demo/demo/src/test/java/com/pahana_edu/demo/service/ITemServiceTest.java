package com.pahana_edu.demo.service;

import com.pahana_edu.demo.dto.ItemDTO;
import com.pahana_edu.demo.model.ItemModel;
import com.pahana_edu.demo.repo.ItemRepo;
//import com.pahana_edu.demo.service.ITemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ITemServiceTest {

    @Mock
    ItemRepo itemRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ITemService itemService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveItem_shouldAutoGenerateCode() {
        // Arrange
        ItemDTO dto = new ItemDTO();
        dto.setItemName("Book A");
        dto.setPrice(new BigDecimal("100.00"));
        dto.setStock("10");

        // No previous items -> should start at ITEM0001
        when(itemRepo.findTopByOrderByItemCodeDesc())
                .thenReturn(null);

        when(modelMapper.map(any(ItemDTO.class), eq(ItemModel.class)))
                .thenReturn(new ItemModel());

        // Act
        ItemDTO saved = itemService.saveItem(dto);

        // Assert
        assertEquals("ITEM0001", saved.getItemCode());
        verify(itemRepo, times(1)).save(any(ItemModel.class));
    }

    @Test
    void saveItem_shouldIncrementFromLastCode() {
        // Arrange
        ItemDTO dto = new ItemDTO();
        dto.setItemName("Book B");
        dto.setPrice(new BigDecimal("50.00"));
        dto.setStock("5");

        // Simulate last code ITEM0007 -> next should be ITEM0008
        ItemModel last = new ItemModel();
        last.setItemCode("ITEM0007");

        when(itemRepo.findTopByOrderByItemCodeDesc())
                .thenReturn(last);

        when(modelMapper.map(any(ItemDTO.class), eq(ItemModel.class)))
                .thenReturn(new ItemModel());

        // Act
        ItemDTO saved = itemService.saveItem(dto);

        // Assert
        assertEquals("ITEM0008", saved.getItemCode());
        verify(itemRepo).save(any(ItemModel.class));
    }

    @Test
    void updateItem_shouldPersistWhenFound() {
        // Arrange
        ItemDTO dto = new ItemDTO();
        dto.setItemCode("ITEM0002");
        dto.setItemName("Updated Name");
        dto.setPrice(new BigDecimal("120.00"));
        dto.setStock("12");

        ItemModel existing = new ItemModel(
                "ITEM0002", "Old Name", new BigDecimal("90.00"), "7");

        when(itemRepo.findById("ITEM0002")).thenReturn(Optional.of(existing));
        when(modelMapper.map(any(ItemDTO.class), eq(ItemModel.class))).thenReturn(existing);
        when(itemRepo.save(any(ItemModel.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        ItemDTO result = itemService.updateItem(dto);

        // Assert
        assertEquals("Updated Name", result.getItemName());
        verify(itemRepo).save(any(ItemModel.class));
    }

    @Test
    void updateItem_shouldThrowWhenNotFound() {
        // Arrange
        ItemDTO dto = new ItemDTO();
        dto.setItemCode("ITEM9999");

        when(itemRepo.findById("ITEM9999")).thenReturn(Optional.empty());

        // Act + Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> itemService.updateItem(dto));
        assertTrue(ex.getMessage().contains("Item not found"));
    }

    @Test
    void deleteItem_shouldThrowWhenNotFound() {
        when(itemRepo.findById("ITEMX")).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> itemService.deleteItem("ITEMX"));
        assertTrue(ex.getMessage().contains("Item not found"));
    }

    @Test
    void deleteItem_shouldDeleteWhenFound() {
        ItemModel existing = new ItemModel("ITEM0003", "Book C", new BigDecimal("10.00"), "3");
        when(itemRepo.findById("ITEM0003")).thenReturn(Optional.of(existing));

        itemService.deleteItem("ITEM0003");

        verify(itemRepo).delete(existing);
    }
}
