package com.medixpress.order_service.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemResponseDTO {
    private String medicineName;
    private Integer quantity;
    private Double pricePerUnit; // price per unit
    private Double totalPrice;
}
