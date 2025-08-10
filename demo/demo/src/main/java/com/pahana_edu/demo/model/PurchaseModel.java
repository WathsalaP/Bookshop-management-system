package com.pahana_edu.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "purchase_model")
public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id") // rename column here
    private Long purchaseId; // renamed from id to purchaseId

    // Patterned purchase code for grouping items, e.g., PRCHS1001
    @Column(nullable = false)
    private String purchaseCode;

    private String customerId;
    private String itemCode;
    private String itemName;

    @Column(precision = 10, scale = 2)
    private BigDecimal itemPrice; // price * quantity saved here

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
