
package com.pahana_edu.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerModel {

    @Id
    @NotBlank(message = "Customer ID cannot be empty or null")
    private String customerId;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Customer address is required")
    private String customerAddress;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\+?\\d{10,12}", message = "Phone must be 10–12 digits")
    private String phoneNumber;

}
