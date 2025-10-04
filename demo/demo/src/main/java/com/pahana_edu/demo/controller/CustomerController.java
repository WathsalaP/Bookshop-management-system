package com.pahana_edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pahana_edu.demo.dto.CustomerDTO;
//import com.pahana_edu.demo.model.CustomerModel;
import com.pahana_edu.demo.service.CustomerService;

//import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getcustomers")
    public List<CustomerDTO> getCustomer() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/addcustomer")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return "Customer added successfully!";
    }

    @PatchMapping("/updatecustomer/{id}")
    public String updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomerId(id); // set customerID from path variable
        customerService.updateCustomer(customerDTO);
        return "Customer updated successfully!";
    }

    @DeleteMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully!";
    }

}
