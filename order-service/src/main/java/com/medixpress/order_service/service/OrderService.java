package com.medixpress.order_service.service;

import com.medixpress.order_service.dto.OrderResponseDTO;
import com.medixpress.order_service.model.Order;
import com.medixpress.order_service.model.OrderStatus;
import jakarta.mail.MessagingException;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId) throws MessagingException;

    List<OrderResponseDTO> getOrdersByUser(Long userId);

    List<OrderResponseDTO> getOrdersByPharmacy(Long pharmacyId);

    OrderResponseDTO getOrderDetails(String orderId);

    Order updateStatusByUser(Long userId, String orderId, OrderStatus status) throws MessagingException;

    Order updateStatusByPharmacy(Long pharmacyId, String orderId, OrderStatus status) throws MessagingException;
}