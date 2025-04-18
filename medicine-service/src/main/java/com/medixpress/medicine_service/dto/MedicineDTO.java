package com.medixpress.medicine_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDTO {

    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private Long pharmacyId;

}

