package com.medixpress.order_service.repository;

import com.medixpress.order_service.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(Long userId);

    List<Order> findByPharmacyId(Long pharmacyId);
}