package com.medixpress.cart_service.service;

import com.medixpress.cart_service.model.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Test
    void testAddToCart() {
        CartItem item = new CartItem(...);
        CartItem savedItem = cartService.addToCart(item);
        assertNotNull(savedItem.getId());
    }
}

