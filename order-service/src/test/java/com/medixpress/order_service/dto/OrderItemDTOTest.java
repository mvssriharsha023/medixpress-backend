package com.medixpress.order_service.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemDTOTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        OrderItemDTO dto = new OrderItemDTO("1", "med123", 2, 50.0, 100.0, "ord456");

        assertEquals("1", dto.getId());
        assertEquals("med123", dto.getMedicineId());
        assertEquals(2, dto.getQuantity());
        assertEquals(50.0, dto.getPricePerUnit());
        assertEquals(100.0, dto.getTotalPrice());
        assertEquals("ord456", dto.getOrderId());
    }

    @Test
    void testBuilder() {
        OrderItemDTO dto = OrderItemDTO.builder()
                .id("1")
                .medicineId("med123")
                .quantity(2)
                .pricePerUnit(50.0)
                .totalPrice(100.0)
                .orderId("ord456")
                .build();

        assertNotNull(dto);
        assertEquals("med123", dto.getMedicineId());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId("1");
        dto.setMedicineId("med123");
        dto.setQuantity(2);
        dto.setPricePerUnit(50.0);
        dto.setTotalPrice(100.0);
        dto.setOrderId("ord456");

        assertEquals("1", dto.getId());
    }
}

