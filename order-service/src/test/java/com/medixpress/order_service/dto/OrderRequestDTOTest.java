package com.medixpress.order_service.dto;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRequestDTOTest {

    @Test
    void testBuilderAndGetters() {
        OrderItemDTO item = new OrderItemDTO("1", "med123", 2, 50.0, 100.0, "ord456");
        OrderRequestDTO request = OrderRequestDTO.builder()
                .userId(10L)
                .pharmacyId(20L)
                .items(List.of(item))
                .build();

        assertEquals(10L, request.getUserId());
        assertEquals(1, request.getItems().size());
    }

    @Test
    void testSetters() {
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setUserId(5L);
        dto.setPharmacyId(15L);

        assertEquals(5L, dto.getUserId());
        assertEquals(15L, dto.getPharmacyId());
    }
}

