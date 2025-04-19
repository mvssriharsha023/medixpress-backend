package com.medixpress.cart_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    private String id;

    private Long userId;
    private Long pharmacyId;
    private String medicineId;
    private Integer quantity;

    private LocalDateTime addedAt;
}
