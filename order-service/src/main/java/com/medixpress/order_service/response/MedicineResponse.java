package com.medixpress.order_service.response;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineResponse {
    private String id;
    private String name;
    private Double price;
    private int quantity;
    private Long pharmacyId;

    // Add other fields if necessary
    // Getters and Setters
}
