package com.medixpress.cart_service.service;

import com.medixpress.cart_service.dto.CartItemDTO;
import com.medixpress.cart_service.model.CartItem;
import com.medixpress.cart_service.test.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private RestTemplate restTemplate;   // 👈 Mock the RestTemplate

    @Test
    void testAddToCart() {
        // Arrange
        CartItemDTO dto = TestDataFactory.createSampleCartItemDTO();

        // Mock behavior: Whenever cartService tries to call restTemplate.getForEntity, return 100 stock
        Mockito.when(restTemplate.getForEntity(anyString(), Mockito.eq(Integer.class)))
                .thenReturn(ResponseEntity.ok(100));

        // Act
        CartItem savedItem = cartService.addToCart(dto);

        // Assert
        assertNotNull(savedItem.getId());
    }
}
