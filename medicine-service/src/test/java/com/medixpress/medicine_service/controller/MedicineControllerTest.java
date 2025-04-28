package com.medixpress.medicine_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medixpress.medicine_service.dto.MedicineDTO;
import com.medixpress.medicine_service.model.Medicine;
import com.medixpress.medicine_service.service.MedicineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MedicineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicineService medicineService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllMedicines() throws Exception {
        when(medicineService.getAllMedicines()).thenReturn(List.of());

        mockMvc.perform(get("/api/medicines"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddMedicine() throws Exception {
        MedicineDTO dto = new MedicineDTO();
        dto.setName("Paracetamol");
        dto.setPrice(50.0);
        dto.setQuantity(10);

        Medicine savedMedicine = new Medicine();
        savedMedicine.setId("1");
        savedMedicine.setName("Paracetamol");

        when(medicineService.addOrUpdateMedicine(any(MedicineDTO.class))).thenReturn(savedMedicine);

        mockMvc.perform(post("/api/medicines/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("id", 1L)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paracetamol"));
    }


    @Test
    void testDeleteMedicine() throws Exception {
        mockMvc.perform(delete("/api/medicines/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Medicine deleted successfully"));
    }

    @Test
    void testReduceMedicineQuantity() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId("1");
        medicine.setQuantity(5);

        when(medicineService.reduceMedicineQuantity(eq("1"), eq(2))).thenReturn(Optional.of(medicine));

        mockMvc.perform(put("/api/medicines/reduce/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testIncreaseMedicineQuantity() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId("1");
        medicine.setQuantity(10);

        when(medicineService.addMedicineQuantity(eq("1"), eq(5))).thenReturn(Optional.of(medicine));

        mockMvc.perform(put("/api/medicines/addQuantity/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testChangePrice() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId("1");
        medicine.setPrice(100.0);

        when(medicineService.changePrice(eq("1"), eq(100.0))).thenReturn(Optional.of(medicine));

        mockMvc.perform(put("/api/medicines/changePrice/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("100.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testGetMedicineById() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId("1");
        medicine.setName("Paracetamol");

        when(medicineService.getMedicineById("1")).thenReturn(medicine);

        mockMvc.perform(get("/api/medicines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paracetamol"));
    }

    @Test
    void testGetAvailableQuantity() throws Exception {
        when(medicineService.getAvailableQuantity("1")).thenReturn(10);

        mockMvc.perform(get("/api/medicines/quantity/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    void testGetMedicineByPharmacy() throws Exception {
        when(medicineService.getAllMedicineByPharmacy(1L)).thenReturn(List.of());

        mockMvc.perform(get("/api/medicines/pharmacy/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchMedicine() throws Exception {
        when(medicineService.searchMedicine(1L, "Paracetamol")).thenReturn(List.of());

        mockMvc.perform(get("/api/medicines/search")
                        .param("name", "Paracetamol")
                        .header("id", 1L))
                .andExpect(status().isOk());
    }
}
