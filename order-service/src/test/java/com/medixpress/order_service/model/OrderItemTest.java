package com.medixpress.order_service.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testOrderItemBuilderAndGetters() {
        OrderItem orderItem = OrderItem.builder()
                .id("item1")
                .medicineId("med1")
                .quantity(3)
                .pricePerUnit(20.0)
                .totalPrice(60.0)
                .orderId("order123")
                .build();

        assertNotNull(orderItem);
        assertEquals("item1", orderItem.getId());
        assertEquals("med1", orderItem.getMedicineId());
        assertEquals(3, orderItem.getQuantity());
        assertEquals(20.0, orderItem.getPricePerUnit());
        assertEquals(60.0, orderItem.getTotalPrice());
        assertEquals("order123", orderItem.getOrderId());
    }
}
