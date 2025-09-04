package com.pahana_edu.demo.service;

import com.pahana_edu.demo.dto.ItemDTO;
import com.pahana_edu.demo.dto.PurchaseDTO;
import com.pahana_edu.demo.model.ItemModel;
import com.pahana_edu.demo.model.PurchaseModel;
import com.pahana_edu.demo.repo.ItemRepo;
import com.pahana_edu.demo.repo.PurchaseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseService {

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ITemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    public String savePurchaseAndCalculate(String customerId, BigDecimal discountPercentage,
            List<PurchaseDTO> purchaseDTOList) {
        String purchaseCode = generatePurchaseCode();

        for (PurchaseDTO purchaseDTO : purchaseDTOList) {
            purchaseDTO.setPurchaseId(null);
            purchaseDTO.setDiscount(discountPercentage);
            purchaseDTO.setCustomerId(customerId);
            purchaseDTO.setPurchaseCode(purchaseCode);

            // Handle item creation/validation
            if (purchaseDTO.getItemCode() == null || purchaseDTO.getItemCode().isEmpty()) {
                ItemDTO newItem = new ItemDTO();
                newItem.setItemName(purchaseDTO.getItemName());
                newItem.setPrice(purchaseDTO.getItemPrice());
                newItem.setStock(String.valueOf(purchaseDTO.getQuantity()));
                ItemDTO savedItem = itemService.saveItem(newItem);
                purchaseDTO.setItemCode(savedItem.getItemCode());
            } else {
                ItemModel existingItem = itemRepo.findById(purchaseDTO.getItemCode()).orElse(null);
                if (existingItem == null) {
                    throw new IllegalArgumentException("ItemCode " + purchaseDTO.getItemCode() + " does not exist!");
                }
            }

            // FIX: Preserve original unit price in the model
            PurchaseModel purchaseModel = modelMapper.map(purchaseDTO, PurchaseModel.class);

            // Add lineTotal field if your model has it, otherwise calculate when needed
            purchaseModel.setLineTotal(
                    purchaseDTO.getItemPrice().multiply(BigDecimal.valueOf(purchaseDTO.getQuantity())));

            purchaseRepo.save(purchaseModel);
        }

        return purchaseCode;
    }

    public List<PurchaseDTO> getPurchaseDetailsByCode(String purchaseCode) {
        List<PurchaseModel> purchases = purchaseRepo.findByPurchaseCode(purchaseCode);
        return purchases.stream()
                .map(p -> {
                    PurchaseDTO dto = modelMapper.map(p, PurchaseDTO.class);
                    // Ensure we're returning the unit price, not line total
                    if (p.getLineTotal() != null) {
                        dto.setItemPrice(p.getItemPrice()); // Preserve original unit price
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTotal(List<PurchaseDTO> purchaseItems, BigDecimal discountPercentage) {
        BigDecimal total = BigDecimal.ZERO;
        for (PurchaseDTO p : purchaseItems) {
            // Use the original unit price and quantity
            BigDecimal lineTotal = p.getItemPrice().multiply(BigDecimal.valueOf(p.getQuantity()));
            total = total.add(lineTotal);
        }

        if (discountPercentage != null && discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal discountAmount = total.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
            total = total.subtract(discountAmount);
        }

        return total.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : total;
    }

    private String generatePurchaseCode() {
        String lastCode = purchaseRepo.findMaxPurchaseCode();
        int nextNum = 1001;
        if (lastCode != null && lastCode.startsWith("PRCHS")) {
            try {
                int num = Integer.parseInt(lastCode.substring(5));
                nextNum = num + 1;
            } catch (NumberFormatException ignored) {
            }
        }
        return "PRCHS" + nextNum;
    }

}