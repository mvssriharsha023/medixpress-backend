package com.medixpress.order_service.controller;

import com.medixpress.order_service.dto.OrderResponseDTO;
import com.medixpress.order_service.model.Order;
import com.medixpress.order_service.model.OrderStatus;
import com.medixpress.order_service.service.OrderService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() throws MessagingException {
        Order mockOrder = new Order();
        when(orderService.placeOrder(1L)).thenReturn(mockOrder);

        ResponseEntity<Order> response = orderController.placeOrder(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    void testGetOrdersByUser() {
        List<OrderResponseDTO> mockOrders = Collections.emptyList();
        when(orderService.getOrdersByUser(1L)).thenReturn(mockOrders);

        ResponseEntity<List<OrderResponseDTO>> response = orderController.getOrdersByUser(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrders, response.getBody());
    }

    @Test
    void testGetOrdersByPharmacy() {
        List<OrderResponseDTO> mockOrders = Collections.emptyList();
        when(orderService.getOrdersByPharmacy(1L)).thenReturn(mockOrders);

        ResponseEntity<List<OrderResponseDTO>> response = orderController.getOrdersByPharmacy(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrders, response.getBody());
    }

    @Test
    void testGetOrderDetails() {
        OrderResponseDTO mockOrder = new OrderResponseDTO();
        when(orderService.getOrderDetails("order123")).thenReturn(mockOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.getOrderDetails("order123");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    void testCancelOrder() throws MessagingException {
        Order mockOrder = new Order();
        when(orderService.updateStatusByUser(1L, "order123", OrderStatus.CANCELLED)).thenReturn(mockOrder);

        ResponseEntity<Order> response = orderController.cancelOrder("order123", 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    void testDeliveredOrder() throws MessagingException {
        Order mockOrder = new Order();
        when(orderService.updateStatusByUser(1L, "order123", OrderStatus.DELIVERED)).thenReturn(mockOrder);

        ResponseEntity<Order> response = orderController.deliveredOrder("order123", 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    void testOutOfDeliveryOrder() throws MessagingException {
        Order mockOrder = new Order();
        when(orderService.updateStatusByPharmacy(1L, "order123", OrderStatus.OUT_OF_DELIVERY)).thenReturn(mockOrder);

        ResponseEntity<Order> response = orderController.outOfDeliveryOrder("order123", 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
    }
}
