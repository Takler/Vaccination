package hu.demo.vaccination.controller;

import hu.demo.vaccination.dto.DoctorCreate;
import hu.demo.vaccination.service.DoctorService;
import hu.demo.vaccination.utility.DoctorInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private DoctorService doctorService;
    @Autowired
    private DoctorInit doctorInit;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Void> createDoctor(@RequestBody DoctorCreate doctorCreate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<DoctorCreate>> getDoctorsList() {
//        return new ResponseEntity<>(List < DoctorCreate >, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<DoctorCreate> getDoctor(@PathVariable int id) {
//        return new ResponseEntity<>(DoctorCreate, HttpStatus.OK);
//    }

    @PutMapping
    public ResponseEntity<Void> updateDoctor(@RequestBody DoctorCreate doctorCreate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/init")
    public void createTable(){
    //public ResponseEntity<Void> createTable(){
    //     return new ResponseEntity<>(doctorInit.createTable(),HttpStatus.OK);
        doctorInit.createTable();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
