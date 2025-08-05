package com.pahana_edu.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pahana_edu.demo.dto.CustomerDTO;
import com.pahana_edu.demo.model.CustomerModel;
import com.pahana_edu.demo.repo.CustomerRepo;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerModel> customersList = customerRepo.findAll();
        return modelMapper.map(customersList, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerRepo.save(modelMapper.map(customerDTO, CustomerModel.class));
        return customerDTO;
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Optional<CustomerModel> optionalCustomer = customerRepo.findById(customerDTO.getCustomerId());

        if (optionalCustomer.isPresent()) {
            CustomerModel existingCustomer = optionalCustomer.get();

            existingCustomer.setCustomerName(customerDTO.getCustomerName());
            existingCustomer.setCustomerAddress(customerDTO.getCustomerAddress());
            existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());

            customerRepo.save(existingCustomer);
            return customerDTO;
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerDTO.getCustomerId());
        }
    }

    public void deleteCustomer(String customerId) {
        Optional<CustomerModel> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent()) {
            customerRepo.delete(optionalCustomer.get());
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

}
