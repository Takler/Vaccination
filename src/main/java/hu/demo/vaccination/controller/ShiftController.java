package hu.demo.vaccination.controller;

import hu.demo.vaccination.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

    private DoctorService doctorService;

    @Autowired
    public ShiftController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<String> getShiftInfo(){
       return new ResponseEntity<>("info", HttpStatus.OK);
   }
}
