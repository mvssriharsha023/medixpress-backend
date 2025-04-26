package com.medixpress.medicine_service.service;

import com.medixpress.medicine_service.dto.MedicineDTO;
import com.medixpress.medicine_service.dto.MedicineSearchDTO;
import com.medixpress.medicine_service.model.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    Medicine addOrUpdateMedicine(MedicineDTO medicineDTO);

    void deleteMedicine(String medicineId);

    Optional<Medicine> reduceMedicineQuantity(String medicineId, Integer quantity);

    Optional<Medicine> addMedicineQuantity(String medicineId, Integer quantity);

    Optional<Medicine> changePrice(String medicineId, Double price);

    List<Medicine> getAllMedicines();

    List<Medicine> getAllMedicineByPharmacy(Long pharmacyId);

    Medicine getMedicineById(String id);

    int getAvailableQuantity(String medicineId);

    List<MedicineSearchDTO> searchMedicine(Long userId, String medicineName);
}
