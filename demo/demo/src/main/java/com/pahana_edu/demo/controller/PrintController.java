package com.pahana_edu.demo.controller;

import com.pahana_edu.demo.dto.PurchaseDTO;
import com.pahana_edu.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class PrintController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = "/print/{purchaseCode}", produces = MediaType.TEXT_HTML_VALUE)
    public String printInvoice(@PathVariable String purchaseCode) { // String for PRCHS1001 pattern
        List<PurchaseDTO> purchases = purchaseService.getPurchaseDetailsByCode(purchaseCode);

        if (purchases.isEmpty()) {
            return "<h3>No purchase found with ID: " + purchaseCode + "</h3>";
        }

        BigDecimal discountPercentage = purchases.get(0).getDiscount() != null ? purchases.get(0).getDiscount()
                : BigDecimal.ZERO;
        BigDecimal finalTotal = purchaseService.calculateTotal(purchases, discountPercentage);

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Invoice - Purchase ID: ").append(purchaseCode).append("</title>")
                .append("<style>")
                .append("table { width: 100%; border-collapse: collapse; }")
                .append("th, td { border: 1px solid black; padding: 8px; text-align: left; }")
                .append("</style></head><body>");
        html.append("<h2>Invoice - Purchase ID: ").append(purchaseCode).append("</h2>");
        html.append("<p>Customer ID: ").append(purchases.get(0).getCustomerId()).append("</p>");
        html.append("<p>Date: ").append(purchases.get(0).getPurchasedDateTime()).append("</p>");
        html.append("<p>Discount: ").append(discountPercentage).append("%</p>");

        html.append(
                "<table><thead><tr><th>Item Code</th><th>Item Name</th><th>Quantity</th><th>Unit Price</th><th>Total Price</th></tr></thead><tbody>");
        BigDecimal subTotal = BigDecimal.ZERO;

        for (PurchaseDTO p : purchases) {
            BigDecimal lineTotal = p.getItemPrice().multiply(BigDecimal.valueOf(p.getQuantity()));
            html.append("<tr>")
                    .append("<td>").append(p.getItemCode()).append("</td>")
                    .append("<td>").append(p.getItemName()).append("</td>")
                    .append("<td>").append(p.getQuantity()).append("</td>")
                    .append("<td>").append(p.getItemPrice()).append("</td>")
                    .append("<td>").append(lineTotal).append("</td>")
                    .append("</tr>");
            subTotal = subTotal.add(lineTotal);
        }

        html.append("<tr><td colspan='4'><b>Subtotal</b></td><td><b>").append(subTotal).append("</b></td></tr>");
        html.append("<tr><td colspan='4'><b>Discount</b></td><td><b>").append(discountPercentage)
                .append("%</b></td></tr>");
        html.append("<tr><td colspan='4'><b>Final Total</b></td><td><b>").append(finalTotal).append("</b></td></tr>");
        html.append("</tbody></table>");

        html.append("<script>window.onload = function() { window.print(); }</script>");

        html.append("</body></html>");
        return html.toString();
    }
}
