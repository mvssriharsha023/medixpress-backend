package com.medixpress.order_service.service;




import com.medixpress.order_service.model.OrderStatus;
import com.medixpress.order_service.response.EmailResponseDTO;
import com.medixpress.order_service.response.ItemResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EmailBuilderTest {

    @Test
    void testBuildCustomerHtmlEmail() {
        EmailBuilder builder = new EmailBuilder();
        EmailResponseDTO dto = new EmailResponseDTO();
        dto.setCustomerName("John");
        dto.setPharmacyName("MedStore");
        dto.setStatus(OrderStatus.PLACED);
        dto.setOrderDateTime(LocalDateTime.now());
        dto.setTotalAmount(150.0);
        dto.setItems(Collections.singletonList(new ItemResponseDTO("Paracetamol", 2, 10.0, 20.0)));

        String html = builder.buildCustomerHtmlEmail(dto);

        assertTrue(html.contains("Dear John"));
        assertTrue(html.contains("Paracetamol"));
        assertTrue(html.contains("Total Amount"));
    }



}

