package com.medixpress.order_service.service;

//import org.junit.jupiter.api.Order;
import com.medixpress.order_service.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void testPlaceOrder() {
        Order order = new Order(...);
        Order savedOrder = orderService.placeOrder(order);
        assertNotNull(savedOrder.getId());
    }
}

