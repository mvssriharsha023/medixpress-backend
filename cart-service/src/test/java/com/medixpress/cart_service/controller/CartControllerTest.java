package com.medixpress.cart_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medixpress.cart_service.dto.CartItemDTO;
import com.medixpress.cart_service.model.CartItem;
import com.medixpress.cart_service.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;  // Mocking service layer

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddItemApi() throws Exception {
        // Arrange
        CartItemDTO dto = new CartItemDTO();
        dto.setUserId(1L);
        dto.setPharmacyId(100L);
        dto.setMedicineId("200");
        dto.setQuantity(2);

        CartItem savedItem = CartItem.builder()
                .id("sample-cart-item-id")
                .userId(1L)
                .pharmacyId(100L)
                .medicineId("200")
                .quantity(2)
                .build();

        when(cartService.addToCart(any(CartItemDTO.class))).thenReturn(savedItem);

        // Act & Assert
        mockMvc.perform(post("/api/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("id", 1L)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
