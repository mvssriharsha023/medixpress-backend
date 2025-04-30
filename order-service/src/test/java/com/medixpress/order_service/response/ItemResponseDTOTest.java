package com.medixpress.order_service.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemResponseDTOTest {

    @Test
    void testItemResponseDTOBuilder() {
        ItemResponseDTO itemResponseDTO = ItemResponseDTO.builder()
                .medicineName("Aspirin")
                .quantity(2)
                .pricePerUnit(10.0)
                .totalPrice(20.0)
                .build();

        assertNotNull(itemResponseDTO);
        assertEquals("Aspirin", itemResponseDTO.getMedicineName());
        assertEquals(2, itemResponseDTO.getQuantity());
        assertEquals(10.0, itemResponseDTO.getPricePerUnit());
        assertEquals(20.0, itemResponseDTO.getTotalPrice());
    }
}
