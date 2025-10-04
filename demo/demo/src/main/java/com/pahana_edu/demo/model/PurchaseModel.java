package com.pahana_edu.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "purchase_model")
public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseId;

    // Patterned purchase code for grouping items, e.g., PRCHS1001
    @Column(nullable = false)
    @NotBlank(message = "Purchase code is required")
    private String purchaseCode;

    private String customerId;
    private String itemCode;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @Column(precision = 10, scale = 2)
    @DecimalMin(value = "0.00", inclusive = false, message = "Unit price must be > 0")
    private BigDecimal itemPrice; // unit price

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal discount; // discount % for this purchase line

    @Column(name = "line_total", precision = 10, scale = 2)
    private BigDecimal lineTotal;

    private LocalDateTime purchasedDateTime;

    @PrePersist
    protected void onCreate() {
        this.purchasedDateTime = LocalDateTime.now();
        this.lineTotal = this.itemPrice.multiply(BigDecimal.valueOf(this.quantity));
    }
}
