/* 
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
    private String userid;
    private String username;
    private String email;
    private String phone_number;
    private String password;
}
*/
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
    private String userid;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone_number;

    @NotBlank(message = "Password is required")
    private String password;
}
