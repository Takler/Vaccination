package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")   //Megnézni a hibát, amit a Józsi mondott Discordon
public class DoctorController {   // Testeket írni!!

    private DoctorService doctorService;

    // deleted JSON kiszedése

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Doctor doctor) {
        if (doctorService.save(doctor)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAll() {    // struktúra??
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getById(@PathVariable int doctorId) {
        Doctor doctor = doctorService.getById(doctorId);
        if (doctor == null) {   // itt akkor null helyett mi?
            return new ResponseEntity<>(doctor, HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Doctor doctor) {
        if (doctorService.update(doctor)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> delete(@PathVariable int doctorId) {
        if (doctorService.delete(doctorId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
