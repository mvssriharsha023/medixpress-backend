package com.medixpress.order_service.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderBuilderAndGetters() {
        OrderItem orderItem = OrderItem.builder()
                .id("item1")
                .medicineId("med1")
                .quantity(2)
                .pricePerUnit(50.0)
                .totalPrice(100.0)
                .orderId("order1")
                .build();

        Order order = Order.builder()
                .id("order1")
                .userId(1L)
                .pharmacyId(10L)
                .totalAmount(100.0)
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.PLACED)
                .items(Collections.singletonList(orderItem))
                .build();

        assertNotNull(order);
        assertEquals("order1", order.getId());
        assertEquals(1L, order.getUserId());
        assertEquals(10L, order.getPharmacyId());
        assertEquals(100.0, order.getTotalAmount());
        assertEquals(OrderStatus.PLACED, order.getStatus());
        assertFalse(order.getItems().isEmpty());
    }
}
