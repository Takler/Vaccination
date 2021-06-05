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
    public ResponseEntity<Void> saveFile(@RequestBody InputCreateData input) {
        boolean saveSuccessful = patientService.fileSave(input);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/file")
    public ResponseEntity<Void> loadFile(@RequestBody InputCreateData input) {
        boolean loadSuccessful = patientService.fileLoad(input);
        if (loadSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patients = patientService.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);   // TODO nem értem? egyszer üres lista van generálva ha rossz, OK -val aztán meg visszamegy?
    }  // TODO nem értem: akkor meg minek a 47-es sor?

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable int id) {
        Patient patient = patientService.getById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  //TODO nem értem?  not found és adatbázis hiba is ezt dobja? -> BAD_REQUEST?
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PatientCreateData data) {
        boolean saveSuccessful = patientService.save(data);  // TODO nem értem: minek ez a sor?
        if (saveSuccessful) {
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
        boolean deleteSuccessful = patientService.delete(id);
        if (deleteSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
