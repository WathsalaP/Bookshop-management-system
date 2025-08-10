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
