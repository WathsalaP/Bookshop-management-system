package com.pahana_edu.demo.dto;

import java.math.BigDecimal;

public class PurchaseDTO {
    private Long purchaseId; // renamed from id to purchaseId
    private String purchaseCode; // PRCHS1001 etc.
    private String customerId;
    private String itemCode;
    private String itemName;
    private BigDecimal itemPrice;
    private int quantity;
    private BigDecimal discount;
    private String purchasedDateTime;

    // Getters and setters

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getPurchasedDateTime() {
        return purchasedDateTime;
    }

    public void setPurchasedDateTime(String purchasedDateTime) {
        this.purchasedDateTime = purchasedDateTime;
    }
}
