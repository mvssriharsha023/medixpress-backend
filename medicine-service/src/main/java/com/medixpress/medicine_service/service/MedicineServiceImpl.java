package com.medixpress.medicine_service.service;

import com.medixpress.medicine_service.dto.MedicineDTO;
import com.medixpress.medicine_service.dto.MedicineSearchDTO;
import com.medixpress.medicine_service.dto.UserDTO;
import com.medixpress.medicine_service.exception.MedicineNotFoundException;
import com.medixpress.medicine_service.exception.NegativeIntegerException;
import com.medixpress.medicine_service.exception.OutOfStockException;
import com.medixpress.medicine_service.model.Medicine;
import com.medixpress.medicine_service.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    private UserDTO fetchUserById(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user-service/user/" + userId;
        System.out.println(url);

        try {
            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
        } catch (Exception e) {
            System.err.println("Error fetching user with ID " + userId + ": " + e.getMessage());
        }

        return null;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS_KM = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

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
        medicine.setQuantity(newQuantity);
        medicineRepository.save(medicine);
        return medicineRepository.findById(medicineId);
    }

    @Override
    public Optional<Medicine> addMedicineQuantity(String medicineId, Integer quantity) {
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new MedicineNotFoundException("Medicine not found"));
        int newQuantity = medicine.getQuantity() + quantity;
        if (newQuantity < 0) {
            throw new OutOfStockException("Not enough quantity to reduce");
        }
        medicine.setQuantity(newQuantity);
        medicineRepository.save(medicine);
        return medicineRepository.findById(medicineId);
    }

    @Override
    public Optional<Medicine> changePrice(String medicineId, Double price) {
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new MedicineNotFoundException("Medicine not found"));
        if (price <= 0) {
            throw new NegativeIntegerException("Cannot set price to zero or negative");
        }
        medicine.setPrice(price);
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

    @Override
    public List<MedicineSearchDTO> searchMedicine(Long userId, String medicineName) {
        List<Medicine> medicineList;

        if (medicineName == null || medicineName.trim().isEmpty()) {
            // Filter all medicines with quantity > 0
            medicineList = medicineRepository.findAll()
                    .stream()
                    .filter(med -> med.getQuantity() > 0)
                    .collect(Collectors.toList());
        } else {
            medicineList = medicineRepository.findByNameAndQuantityGreaterThan(medicineName, 0);
        }

        if (medicineList.isEmpty()) return Collections.emptyList();

        // Step 1: Get requesting user's location
        UserDTO user = fetchUserById(userId);
        double userLat = user.getLatitude();
        double userLng = user.getLongitude();

        List<MedicineSearchDTO> dtoList = new ArrayList<>();

        for (Medicine medicine : medicineList) {
            UserDTO pharmacy = fetchUserById(medicine.getPharmacyId());
            if (pharmacy == null) continue;

            double distance = calculateDistance(userLat, userLng, pharmacy.getLatitude(), pharmacy.getLongitude());

            MedicineSearchDTO dto = MedicineSearchDTO.builder()
                    .id(medicine.getId())
                    .userId(userId)
                    .name(medicine.getName())
                    .price(medicine.getPrice())
                    .quantity(medicine.getQuantity())
                    .pharmacyId(pharmacy.getId())
                    .pharmacyName(pharmacy.getName())
                    .distance(distance)
                    .build();

            dtoList.add(dto);
        }

        dtoList.sort(Comparator
                .comparingDouble(MedicineSearchDTO::getDistance)
                .thenComparingDouble(MedicineSearchDTO::getPrice));

        return dtoList;
    }




}
