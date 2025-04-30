package com.medixpress.order_service.response;

import com.medixpress.order_service.model.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EmailResponseDTOTest {

    @Test
    void testEmailResponseDTOBuilder() {
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO("Aspirin", 2, 10.0, 20.0);

        EmailResponseDTO emailResponseDTO = EmailResponseDTO.builder()
                .customerName("John Doe")
                .pharmacyName("PharmaPlus")
                .totalAmount(100.0)
                .status(OrderStatus.PLACED)
                .orderDateTime(LocalDateTime.now())
                .items(Collections.singletonList(itemResponseDTO))
                .build();

        assertNotNull(emailResponseDTO);
        assertEquals("John Doe", emailResponseDTO.getCustomerName());
        assertEquals("PharmaPlus", emailResponseDTO.getPharmacyName());
        assertEquals(100.0, emailResponseDTO.getTotalAmount());
        assertEquals(OrderStatus.PLACED, emailResponseDTO.getStatus());
        assertNotNull(emailResponseDTO.getOrderDateTime());
        assertFalse(emailResponseDTO.getItems().isEmpty());
    }
}
