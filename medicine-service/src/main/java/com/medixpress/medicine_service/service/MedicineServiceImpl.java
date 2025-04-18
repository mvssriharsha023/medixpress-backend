package com.medixpress.medicine_service.service;

import com.medixpress.medicine_service.dto.MedicineDTO;
import com.medixpress.medicine_service.exception.MedicineNotFoundException;
import com.medixpress.medicine_service.exception.OutOfStockException;
import com.medixpress.medicine_service.model.Medicine;
import com.medixpress.medicine_service.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addOrUpdateMedicine(MedicineDTO medicineDTO) {

        // Check if the medicine already exists for this pharmacy
        Medicine existingMedicine = medicineRepository.findByNameAndPharmacyId(medicineDTO.getName(), medicineDTO.getPharmacyId());
        if (existingMedicine != null) {
            existingMedicine.setQuantity(existingMedicine.getQuantity() + medicineDTO.getQuantity());
            return medicineRepository.save(existingMedicine);
        }
        // Create a new medicine
        Medicine newMedicine = new Medicine();
        newMedicine.setName(medicineDTO.getName());
        newMedicine.setPrice(medicineDTO.getPrice());
        newMedicine.setQuantity(medicineDTO.getQuantity());
        newMedicine.setPharmacyId(medicineDTO.getPharmacyId());
        return medicineRepository.save(newMedicine);

    }

    @Override
    public void deleteMedicine(String medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    @Override
    public Optional<Medicine> reduceMedicineQuantity(String medicineId, Integer quantity) {
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new MedicineNotFoundException("Medicine not found"));
        int newQuantity = medicine.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new OutOfStockException("Not enough quantity to reduce");
        }
        if (newQuantity == 0) {
            return Optional.empty();
        }
        medicine.setQuantity(newQuantity);
        medicineRepository.save(medicine);
        return medicineRepository.findById(medicineId);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }


    @Override
    public List<Medicine> getAllMedicineByPharmacy(Long pharmacyId) {
        return medicineRepository.findByPharmacyId(pharmacyId);
    }

    @Override
    public Medicine getMedicineById(String id) {
        return medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException("Medicine not found"));
    }

    @Override
    public int getAvailableQuantity(String medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine not found in this pharmacy"));

        return medicine.getQuantity();
    }

}
