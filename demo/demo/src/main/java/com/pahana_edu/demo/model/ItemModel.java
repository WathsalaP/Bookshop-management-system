/* 
package com.pahana_edu.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemModel {
    @Id
    private String itemCode;
    private String itemName;
    private BigDecimal price;
    private String stock;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
*/

package com.pahana_edu.demo.model;

import java.math.BigDecimal;

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
public class ItemModel {
    @Id
    private String itemCode;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be > 0")
    private BigDecimal price;

    // Keeping as String to match your current schema; validate when parsing in
    // service if needed
    private String stock;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
