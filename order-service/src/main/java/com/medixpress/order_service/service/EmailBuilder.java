package com.medixpress.order_service.service;

import com.medixpress.order_service.model.OrderStatus;
import com.medixpress.order_service.response.EmailResponseDTO;
import com.medixpress.order_service.response.ItemResponseDTO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailBuilder {

    public String buildCustomerHtmlEmail(EmailResponseDTO dto) {
        String statusMessage;
        switch (dto.getStatus()) {
            case OrderStatus.CANCELLED:
                statusMessage = "<p style='color:red;'>Your order has been <strong>CANCELLED</strong>.</p>";
                break;
            case OrderStatus.DELIVERED:
                statusMessage = "<p style='color:green;'>Your order has been <strong>DELIVERED</strong>.</p>";
                break;
            case OrderStatus.OUT_OF_DELIVERY:
                statusMessage = "<p style='color:orange;'>Your order is <strong>OUT FOR DELIVERY</strong>.</p>";
                break;
            default:
                statusMessage = "<p>Your order status: <strong>" + dto.getStatus() + "</strong></p>";
        }

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>Order Update - ").append(dto.getPharmacyName()).append("</h2>");
        html.append("<p>Dear ").append(dto.getCustomerName()).append(",</p>");
        html.append(statusMessage);
        html.append("<p><strong>Order Date & Time:</strong> ").append(dto.getOrderDateTime()).append("</p>");
        html.append("<h3>Items:</h3>");
        html.append("<table border='1' cellpadding='5' cellspacing='0'>");
        html.append("<tr><th>Medicine</th><th>Quantity</th><th>Price per Unit</th><th>Total Price</th></tr>");
        for (ItemResponseDTO item : dto.getItems()) {
            html.append("<tr>")
                    .append("<td>").append(item.getMedicineName()).append("</td>")
                    .append("<td>").append(item.getQuantity()).append("</td>")
                    .append("<td>₹").append(item.getPricePerUnit()).append("</td>")
                    .append("<td>₹").append(item.getTotalPrice()).append("</td>")
                    .append("</tr>");
        }
        html.append("</table>");
        html.append("<p><strong>Total Amount:</strong> ₹").append(dto.getTotalAmount()).append("</p>");
        html.append("<p>Thank you for choosing ").append(dto.getPharmacyName()).append("!</p>");
        html.append("</body></html>");
        return html.toString();
    }

    public String buildPharmacyHtmlEmail(EmailResponseDTO dto) {
        String statusMessage;
        switch (dto.getStatus()) {
            case OrderStatus.CANCELLED:
                statusMessage = "<p style='color:red;'>The order has been <strong>CANCELLED</strong> by the customer.</p>";
                break;
            case OrderStatus.DELIVERED:
                statusMessage = "<p style='color:green;'>The order has been <strong>DELIVERED</strong> successfully.</p>";
                break;
            case OrderStatus.OUT_OF_DELIVERY:
                statusMessage = "<p style='color:orange;'>The order is <strong>OUT FOR DELIVERY</strong>.</p>";
                break;
            default:
                statusMessage = "<p>Order status: <strong>" + dto.getStatus() + "</strong></p>";
        }

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>Customer Order Update</h2>");
        html.append("<p><strong>Customer:</strong> ").append(dto.getCustomerName()).append("</p>");
        html.append(statusMessage);
        html.append("<p><strong>Order Date & Time:</strong> ").append(dto.getOrderDateTime()).append("</p>");
        html.append("<h3>Items Ordered:</h3>");
        html.append("<table border='1' cellpadding='5' cellspacing='0'>");
        html.append("<tr><th>Medicine</th><th>Quantity</th><th>Price per Unit</th><th>Total Price</th></tr>");
        for (ItemResponseDTO item : dto.getItems()) {
            html.append("<tr>")
                    .append("<td>").append(item.getMedicineName()).append("</td>")
                    .append("<td>").append(item.getQuantity()).append("</td>")
                    .append("<td>₹").append(item.getPricePerUnit()).append("</td>")
                    .append("<td>₹").append(item.getTotalPrice()).append("</td>")
                    .append("</tr>");
        }
        html.append("</table>");
        html.append("<p><strong>Total Amount:</strong> ₹").append(dto.getTotalAmount()).append("</p>");
        html.append("<p>Please take action accordingly.</p>");
        html.append("</body></html>");
        return html.toString();
    }


}
