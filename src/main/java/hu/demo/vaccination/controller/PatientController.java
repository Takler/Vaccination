package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/file")
    public ResponseEntity<Void> fileSave(@RequestBody InputCreateData input) {
        if (patientService.fileSave(input)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/file")
    public ResponseEntity<Void> fileLoad(@RequestBody InputCreateData input) {
        if (patientService.fileLoad(input)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lastnames/{firstName}")
    public ResponseEntity<List<String>> getLastNames(@PathVariable String firstName) {
        List<String> lastNames = patientService.getLastNames(firstName);
        if (!lastNames.isEmpty()) {
            return new ResponseEntity<>(lastNames, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<String> getName(@PathVariable int id) {
        String name = patientService.getName(id);
        if (!name.isEmpty()) {
            return new ResponseEntity<>(name, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patients = patientService.findAll();
        if (!patients.isEmpty()) {
            return new ResponseEntity<>(patients, HttpStatus.OK);   // TODO nem értem? egyszer üres lista van generálva ha rossz, OK -val aztán meg visszamegy?
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }  // TODO nem értem: akkor meg minek a 47-es sor?

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable int id) {
        Patient patient = patientService.getById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  //TODO nem értem?  not found és adatbázis hiba is ezt dobja? -> BAD_REQUEST?
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PatientCreateData data) {
        if (patientService.save(data)) {// TODO nem értem: minek ez a sor?
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody PatientCreateData data) {
        boolean updateSuccessful = patientService.update(id, data);
        if (updateSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  //TODO itt értem, hogy miért nem NOT_MODIFIED, lásd. getByID
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (patientService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
