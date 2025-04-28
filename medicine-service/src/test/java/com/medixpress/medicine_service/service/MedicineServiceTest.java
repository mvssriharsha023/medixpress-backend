package com.medixpress.medicine_service.service;

import com.medixpress.medicine_service.model.Medicine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

@SpringBootTest
public class MedicineServiceTest {

    @Autowired
    private MedicineService medicineService;

    @Test
    void testGetAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        assertNotNull(medicines);
    }
}

