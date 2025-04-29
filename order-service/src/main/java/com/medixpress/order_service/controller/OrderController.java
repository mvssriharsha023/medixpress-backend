package com.medixpress.order_service.controller;

import com.medixpress.order_service.dto.OrderResponseDTO;
import com.medixpress.order_service.model.Order;
import com.medixpress.order_service.model.OrderStatus;
import com.medixpress.order_service.service.EmailService;
import com.medixpress.order_service.service.OrderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;




    // Place a new order (from user's cart)
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestHeader("id") Long id) throws MessagingException {
        return ResponseEntity.ok(orderService.placeOrder(id));
    }

    // Get all orders by user
    @GetMapping("/user")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUser(@RequestHeader("id") Long id) {
        return ResponseEntity.ok(orderService.getOrdersByUser(id));
    }

    // Get all orders by pharmacy
    @GetMapping("/pharmacy")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByPharmacy(@RequestHeader("id") Long pharmacyId) {
        return ResponseEntity.ok(orderService.getOrdersByPharmacy(pharmacyId));
    }

//     Get detailed order info
    @GetMapping("/get/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderDetails(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }

    // Update status to CANCELLED
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable String orderId, @RequestHeader("id") Long userId) throws MessagingException {
        return ResponseEntity.ok(orderService.updateStatusByUser(userId, orderId, OrderStatus.CANCELLED));
    }

    @PostMapping("/delivered/{orderId}")
    public ResponseEntity<Order> deliveredOrder(@PathVariable String orderId, @RequestHeader("id") Long userId) throws MessagingException {
        return ResponseEntity.ok(orderService.updateStatusByUser(userId, orderId, OrderStatus.DELIVERED));
    }

    @PostMapping("/outofdelivery/{orderId}")
    public ResponseEntity<Order> outOfDeliveryOrder(@PathVariable String orderId, @RequestHeader("id") Long pharmacyId) throws MessagingException {
        return ResponseEntity.ok(orderService.updateStatusByPharmacy(pharmacyId, orderId, OrderStatus.OUT_OF_DELIVERY));
    }
}
