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

public class UserModel {
    @Id
    private String userID;
    private String username;
    private String email;
    private String phoneNumber;
}
