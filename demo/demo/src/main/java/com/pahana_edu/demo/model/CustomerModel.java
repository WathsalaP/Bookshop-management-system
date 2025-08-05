package com.pahana_edu.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerModel {
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String phoneNumber;

}
