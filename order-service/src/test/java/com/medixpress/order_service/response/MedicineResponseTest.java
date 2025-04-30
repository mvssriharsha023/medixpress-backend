package com.medixpress.order_service.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicineResponseTest {

    @Test
    void testMedicineResponseBuilder() {
        MedicineResponse medicineResponse = MedicineResponse.builder()
                .id("1")
                .name("Aspirin")
                .price(10.0)
                .quantity(100)
                .pharmacyId(456L)
                .build();

        assertNotNull(medicineResponse);
        assertEquals("1", medicineResponse.getId());
        assertEquals("Aspirin", medicineResponse.getName());
        assertEquals(10.0, medicineResponse.getPrice());
        assertEquals(100, medicineResponse.getQuantity());
        assertEquals(456L, medicineResponse.getPharmacyId());
    }
}
