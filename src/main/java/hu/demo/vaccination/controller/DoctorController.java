package hu.demo.vaccination.controller;

import hu.demo.vaccination.dto.DoctorCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired


    @PostMapping
    public ResponseEntity<Void> createDoctor(@RequestBody DoctorCreate doctorCreate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorCreate>> getDoctorsList() {
        return new ResponseEntity<>(List < DoctorCreate >, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorCreate> getDoctor(@PathVariable int id) {
        return new ResponseEntity<>(DoctorCreate, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateDoctor(@RequestBody DoctorCreate doctorCreate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
