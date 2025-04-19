package com.medixpress.order_service.repository;

import com.medixpress.order_service.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    List<OrderItem> findByOrderId(String orderId);
}

