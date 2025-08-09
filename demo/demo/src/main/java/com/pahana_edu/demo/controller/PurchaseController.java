package com.pahana_edu.demo.controller;

import com.pahana_edu.demo.dto.PurchaseDTO;
import com.pahana_edu.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase/save")
    public PurchaseSaveResponse savePurchaseAndCalculate(@RequestBody PurchaseSaveRequest request) {
        String purchaseCode = purchaseService.savePurchaseAndCalculate(
                request.getCustomerId(),
                request.getDiscountPercentage(),
                request.getPurchaseItems());

        List<PurchaseDTO> purchases = purchaseService.getPurchaseDetailsByCode(purchaseCode);
        BigDecimal finalBill = purchaseService.calculateTotal(purchases, request.getDiscountPercentage());

        return new PurchaseSaveResponse(purchaseCode, purchases, finalBill);
    }

    @GetMapping("/purchase/{purchaseCode}")
    public List<PurchaseDTO> getPurchase(@PathVariable String purchaseCode) {
        return purchaseService.getPurchaseDetailsByCode(purchaseCode);
    }

    // DTO for incoming request
    public static class PurchaseSaveRequest {
        private String customerId;
        private BigDecimal discountPercentage;
        private List<PurchaseDTO> purchaseItems;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public BigDecimal getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(BigDecimal discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public List<PurchaseDTO> getPurchaseItems() {
            return purchaseItems;
        }

        public void setPurchaseItems(List<PurchaseDTO> purchaseItems) {
            this.purchaseItems = purchaseItems;
        }
    }

    // DTO for outgoing response
    public static class PurchaseSaveResponse {
        private String purchaseCode;
        private List<PurchaseDTO> purchaseItems;
        private BigDecimal finalBill;

        public PurchaseSaveResponse(String purchaseCode, List<PurchaseDTO> purchaseItems, BigDecimal finalBill) {
            this.purchaseCode = purchaseCode;
            this.purchaseItems = purchaseItems;
            this.finalBill = finalBill;
        }

        public String getPurchaseCode() {
            return purchaseCode;
        }

        public void setPurchaseCode(String purchaseCode) {
            this.purchaseCode = purchaseCode;
        }

        public List<PurchaseDTO> getPurchaseItems() {
            return purchaseItems;
        }

        public void setPurchaseItems(List<PurchaseDTO> purchaseItems) {
            this.purchaseItems = purchaseItems;
        }

        public BigDecimal getFinalBill() {
            return finalBill;
        }

        public void setFinalBill(BigDecimal finalBill) {
            this.finalBill = finalBill;
        }
    }
}
