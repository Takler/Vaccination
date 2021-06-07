package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.vaccine.VaccineCreateData;
import hu.demo.vaccination.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> getVaccines() {
        List<Vaccine> vaccines = vaccineService.findAll();
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccine(@PathVariable int id) {
        Vaccine vaccine = vaccineService.getById(id);
        if (vaccine != null) {
            return new ResponseEntity<>(vaccine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createVaccine(@RequestBody VaccineCreateData data) {
        boolean saveSuccessful = vaccineService.save(data);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVaccine(@PathVariable int id, @RequestBody VaccineCreateData data) {
        boolean updateSuccessful = vaccineService.update(id, data);
        if (updateSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable int id) {
        boolean deleteSuccessful = vaccineService.delete(id);
        if (deleteSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Vaccine>> getVaccineForPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(vaccineService.getVaccineForPatient(patient), HttpStatus.OK);
    }
}
