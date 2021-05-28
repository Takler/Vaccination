package hu.demo.vaccination.controller;

import hu.demo.vaccination.dto.DoctorCreate;
import hu.demo.vaccination.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private List<String> aa;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Void> createDoctor(@RequestBody DoctorCreate doctorCreate) {
        if (doctorService.createDoctor(doctorCreate) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getDoctorsList() {    // struktúra??
        return new ResponseEntity<>(doctorService.getDoctorslist(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorCreate> getDoctor(@PathVariable int id) {
        DoctorCreate doctor = doctorService.getDoctor(id);
        if (doctor == null) {
            return new ResponseEntity<>(doctor, HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateDoctor(@RequestBody DoctorCreate doctorCreate) {
        if (doctorService.updateDoctor(doctorCreate) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        if (doctorService.deletedDoctor(id) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
