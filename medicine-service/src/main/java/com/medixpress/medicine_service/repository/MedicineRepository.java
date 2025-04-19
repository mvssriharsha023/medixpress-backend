package com.medixpress.medicine_service.repository;

import com.medixpress.medicine_service.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {

    Medicine findByNameAndPharmacyId(String name, Long pharmacyId);

    List<Medicine> findByPharmacyId(Long pharmacyId);

    List<Medicine> findByName(String medicineName);

    List<Medicine> findByNameAndQuantityGreaterThan(String medicineName, int quantity);

}
