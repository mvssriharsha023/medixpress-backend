package com.medixpress.medicine_service.controller;

import com.medixpress.medicine_service.dto.MedicineDTO;
import com.medixpress.medicine_service.dto.MedicineSearchDTO;
import com.medixpress.medicine_service.model.Medicine;
import com.medixpress.medicine_service.service.MedicineService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<Medicine> addMedicine(@RequestHeader("id") Long pharmacyId, @RequestBody MedicineDTO dto) {
        dto.setPharmacyId(pharmacyId);
        return ResponseEntity.ok(medicineService.addOrUpdateMedicine(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable String id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Medicine deleted successfully");
    }

    @PutMapping("/reduce/{id}")
    public ResponseEntity<Optional<Medicine>> reduceMedicine(@PathVariable String id, @RequestBody Integer quantity) {
        Optional<Medicine> medicine = medicineService.reduceMedicineQuantity(id, quantity);
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/addQuantity/{id}")
    public ResponseEntity<Optional<Medicine>> increaseMedicine(@PathVariable String id, @RequestBody Integer quantity) {
        Optional<Medicine> medicine = medicineService.addMedicineQuantity(id, quantity);
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/changePrice/{id}")
    public ResponseEntity<Optional<Medicine>> changePriceOfMedicine(@PathVariable String id, @RequestBody Double price) {
        Optional<Medicine> medicine = medicineService.changePrice(id, price);
        return ResponseEntity.ok(medicine);
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @GetMapping("/pharmacy/{pharmacyId}")
    public ResponseEntity<List<Medicine>> getMedicine(@PathVariable Long pharmacyId) {
        return ResponseEntity.ok(medicineService.getAllMedicineByPharmacy(pharmacyId));
    }

    @GetMapping("/quantity/{id}")
    public ResponseEntity<Integer> getAvailableQuantity(@PathVariable String id) {
        return ResponseEntity.ok(medicineService.getAvailableQuantity(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable String id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicineSearchDTO>> searchMedicine(
            @RequestParam(value = "name", defaultValue = "") String medicineName,
            @RequestHeader("id") Long userId) {

        return ResponseEntity.ok(medicineService.searchMedicine(userId, medicineName));
    }

}
