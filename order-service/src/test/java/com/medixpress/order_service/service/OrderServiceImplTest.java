package com.medixpress.order_service.service;

import com.medixpress.order_service.exception.OrderNotFoundException;
import com.medixpress.order_service.model.Order;
import com.medixpress.order_service.model.OrderItem;
import com.medixpress.order_service.model.OrderStatus;
import com.medixpress.order_service.repository.OrderRepository;
import com.medixpress.order_service.response.MedicineResponse;
import com.medixpress.order_service.response.UserDTO;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private OrderRepository orderRepository;
    private EmailService emailService;
    private EmailBuilder emailBuilder;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        emailService = mock(EmailService.class);
        emailBuilder = new EmailBuilder();
    }


}
