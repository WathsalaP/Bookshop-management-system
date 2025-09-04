/*package com.pahana_edu.demo.dto;

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
    private BigDecimal lineTotal;

    // Getters and setters

    public PurchaseDTO(Object object, String string, String string2, String string3, String string4, BigDecimal valueOf,
            int i, BigDecimal zero, BigDecimal valueOf2, Object object2) {
        //TODO Auto-generated constructor stub
    }

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

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
*/

package com.pahana_edu.demo.dto;

import java.math.BigDecimal;

public class PurchaseDTO {
    private Long purchaseId;
    private String purchaseCode;
    private String customerId;
    private String itemCode;
    private String itemName;
    private BigDecimal itemPrice;
    private int quantity;
    private BigDecimal discount;
    private String purchasedDateTime;
    private BigDecimal lineTotal;

    // Proper constructor
    public PurchaseDTO(Long purchaseId, String purchaseCode, String customerId, String itemCode, String itemName,
            BigDecimal itemPrice, int quantity, BigDecimal discount, BigDecimal lineTotal, String purchasedDateTime) {
        this.purchaseId = purchaseId;
        this.purchaseCode = purchaseCode;
        this.customerId = customerId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.lineTotal = lineTotal;
        this.purchasedDateTime = purchasedDateTime;
    }

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

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
