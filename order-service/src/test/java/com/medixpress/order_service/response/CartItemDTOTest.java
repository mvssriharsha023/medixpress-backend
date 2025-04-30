package com.medixpress.order_service.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CartItemDTOTest {

    @Test
    void testCartItemDTOBuilder() {
        CartItemDTO cartItemDTO = CartItemDTO.builder()
                .id("1")
                .userId(123L)
                .pharmacyId(456L)
                .medicineId("abc")
                .quantity(2)
                .addedAt(LocalDateTime.now())
                .build();

        assertNotNull(cartItemDTO);
        assertEquals("1", cartItemDTO.getId());
        assertEquals(123L, cartItemDTO.getUserId());
        assertEquals(456L, cartItemDTO.getPharmacyId());
        assertEquals("abc", cartItemDTO.getMedicineId());
        assertEquals(2, cartItemDTO.getQuantity());
        assertNotNull(cartItemDTO.getAddedAt());
    }
}
