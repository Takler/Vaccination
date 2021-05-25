package hu.demo.vaccination.controller;

import hu.demo.vaccination.dto.DoctorCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @GetMapping
    public ResponseEntity<List<DoctorCreate>> getDoctor(){

        return
    }

}
