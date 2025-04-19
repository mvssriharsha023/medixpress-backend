package com.medixpress.cart_service.repository;

import com.medixpress.cart_service.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<CartItem, String> {

    List<CartItem> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
