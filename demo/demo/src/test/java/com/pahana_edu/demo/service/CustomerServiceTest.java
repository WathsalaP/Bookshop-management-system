package com.pahana_edu.demo.service;

import com.pahana_edu.demo.dto.CustomerDTO;
import com.pahana_edu.demo.model.CustomerModel;
import com.pahana_edu.demo.repo.CustomerRepo;
// import com.pahana_edu.demo.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepo customerRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CustomerService customerService;

    @Test
    void saveCustomer_shouldPersist() {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId("C001");
        dto.setCustomerName("Alice");
        dto.setCustomerAddress("10, Main St");
        dto.setPhoneNumber("0771234567");

        when(modelMapper.map(dto, CustomerModel.class)).thenReturn(
                new CustomerModel(
                        dto.getCustomerId(),
                        dto.getCustomerName(),
                        dto.getCustomerAddress(),
                        dto.getPhoneNumber()));

        CustomerDTO saved = customerService.saveCustomer(dto);
        assertEquals("C001", saved.getCustomerId());
        verify(customerRepo).save(any(CustomerModel.class));
    }

    @Test
    void updateCustomer_shouldUpdateWhenExists() {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId("C001");
        dto.setCustomerName("Alice Updated");
        dto.setCustomerAddress("20, High St");
        dto.setPhoneNumber("0771234567");

        CustomerModel existing = new CustomerModel(
                "C001",
                "Alice",
                "Old",
                "0771234567");

        when(customerRepo.findById("C001")).thenReturn(Optional.of(existing));

        CustomerDTO updated = customerService.updateCustomer(dto);
        assertEquals("Alice Updated", updated.getCustomerName());
        verify(customerRepo).save(any(CustomerModel.class));
    }

    @Test
    void updateCustomer_shouldThrowWhenNotFound() {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId("C999");

        when(customerRepo.findById("C999")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> customerService.updateCustomer(dto));

        assertTrue(ex.getMessage().contains("Customer not found"));
    }
}
