package com.pahana_edu.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel {
    @Id
    @NotBlank(message = "User ID cannot be empty or null")
    private String userid;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "\\+?\\d{10,12}", message = "Phone must be 10–12 digits")
    private String phone_number;

    @NotBlank(message = "Password is required")
    private String password;
}
