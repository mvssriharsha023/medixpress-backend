package com.medixpress.order_service.dto;

import com.medixpress.order_service.model.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderResponseDTOTest {

    @Test
    void testAllArgsConstructor() {
        OrderItemDTO item = new OrderItemDTO();
        LocalDateTime now = LocalDateTime.now();
        OrderResponseDTO response = new OrderResponseDTO("1", 10L, 20L, 500.0, OrderStatus.PLACED, now, List.of(item));

        assertEquals(OrderStatus.PLACED, response.getStatus());
        assertEquals(now, response.getOrderDateTime());
    }

    @Test
    void testBuilder() {
        OrderResponseDTO dto = OrderResponseDTO.builder()
                .id("1")
                .userId(10L)
                .pharmacyId(20L)
                .totalAmount(100.0)
                .status(OrderStatus.DELIVERED)
                .orderDateTime(LocalDateTime.now())
                .items(List.of())
                .build();

        assertNotNull(dto);
        assertEquals(OrderStatus.DELIVERED, dto.getStatus());
    }

    @Test
    void testSetters() {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setUserId(100L);
        assertEquals(100L, dto.getUserId());
    }
}

