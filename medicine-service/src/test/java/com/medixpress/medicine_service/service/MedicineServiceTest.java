package com.medixpress.medicine_service.service;

import com.medixpress.medicine_service.model.Medicine;
import com.medixpress.medicine_service.repository.MedicineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicineServiceTest {

    @Autowired
    private MedicineService medicineService;

    @MockBean
    private MedicineRepository medicineRepository;

    @Test
    void testGetAllMedicines() {
        when(medicineRepository.findAll()).thenReturn(List.of(new Medicine()));

        List<Medicine> medicines = medicineService.getAllMedicines();
        assertNotNull(medicines);
        assertEquals(1, medicines.size());
    }

    @Test
    void testGetMedicineById() {
        Medicine medicine = new Medicine();
        medicine.setId("1");

        when(medicineRepository.findById("1")).thenReturn(Optional.of(medicine));

        Medicine result = medicineService.getMedicineById("1");
        assertNotNull(result);
        assertEquals("1", result.getId());
    }
}
