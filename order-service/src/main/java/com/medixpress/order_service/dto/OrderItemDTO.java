package com.medixpress.order_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private String id;
    private String medicineId;
    private Integer quantity;
    private Double pricePerUnit; // price per unit
    private Double totalPrice;
    private String orderId;
}

