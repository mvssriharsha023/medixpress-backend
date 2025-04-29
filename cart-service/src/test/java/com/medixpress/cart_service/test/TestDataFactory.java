package com.medixpress.cart_service.test;


import com.medixpress.cart_service.dto.CartItemDTO;
import com.medixpress.cart_service.model.CartItem;

import java.time.LocalDateTime;

public class TestDataFactory {

    public static CartItemDTO createSampleCartItemDTO() {
        CartItemDTO dto = new CartItemDTO();
        dto.setUserId(1L);
        dto.setPharmacyId(100L);
        dto.setMedicineId("200");
        dto.setQuantity(3);
        return dto;
    }

    public static CartItem createSampleCartItem() {
        return CartItem.builder()
                .id("sample-id")
                .userId(1L)
                .pharmacyId(100L)
                .medicineId("200")
                .quantity(3)
                .addedAt(LocalDateTime.now())
                .build();
    }
}

