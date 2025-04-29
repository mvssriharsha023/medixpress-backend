package com.medixpress.order_service.service;

import com.medixpress.order_service.dto.OrderResponseDTO;
import com.medixpress.order_service.model.Order;
import com.medixpress.order_service.model.OrderStatus;
import com.medixpress.order_service.repository.OrderItemRepository;
import com.medixpress.order_service.repository.OrderRepository;
import com.medixpress.order_service.response.CartItemDTO;
import com.medixpress.order_service.response.MedicineResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_CartEmpty() {
        when(restTemplate.exchange(
                anyString(),
                any(),
                any(),
                any(Class.class))
        ).thenReturn(ResponseEntity.ok(Collections.emptyList()));

        assertThrows(Exception.class, () -> orderService.placeOrder(1L));
    }

    @Test
    void testGetOrdersByUser() {
        when(orderRepository.findByUserId(1L)).thenReturn(Collections.emptyList());

        var orders = orderService.getOrdersByUser(1L);

        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    @Test
    void testGetOrdersByPharmacy() {
        when(orderRepository.findByPharmacyId(1L)).thenReturn(Collections.emptyList());

        var orders = orderService.getOrdersByPharmacy(1L);

        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    @Test
    void testGetOrderDetails_NotFound() {
        when(orderRepository.findById("order123")).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> orderService.getOrderDetails("order123"));
    }

    @Test
    void testUpdateStatusByUser_Cancelled() {
        Order order = new Order();
        order.setStatus(OrderStatus.PLACED);
        order.setItems(Collections.emptyList());

        when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

        var updatedOrder = orderService.updateStatusByUser(1L, "order123", OrderStatus.CANCELLED);

        assertEquals(OrderStatus.CANCELLED, updatedOrder.getStatus());
    }

    @Test
    void testUpdateStatusByPharmacy_OutOfDelivery() {
        Order order = new Order();
        order.setStatus(OrderStatus.PLACED);
        order.setPharmacyId(1L);

        when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

        var updatedOrder = orderService.updateStatusByPharmacy(1L, "order123", OrderStatus.OUT_OF_DELIVERY);

        assertEquals(OrderStatus.OUT_OF_DELIVERY, updatedOrder.getStatus());
    }
}
