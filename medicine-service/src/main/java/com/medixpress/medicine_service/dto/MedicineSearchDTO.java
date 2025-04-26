package com.medixpress.medicine_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineSearchDTO {

    private String id;
    private Long userId;
    private String name;
    private Double price;
    private Integer quantity;
    private Long pharmacyId;
    private String pharmacyName;
    private Double distance;

}

