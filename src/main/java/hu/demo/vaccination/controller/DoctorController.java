package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import hu.demo.vaccination.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")   // TODO Megnézni a hibát, amit a Józsi mondott Discordon
public class DoctorController {   // TODO Testeket írni!!

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // @GetMapping("/file")
    // @PostMapping("/file")

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() { //TODO Miért public mindegyik?
        List<Doctor> doctorList = doctorService.findAll();
        if (!doctorList.isEmpty()) {
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getById(@PathVariable int doctorId) {
        Doctor doctor = doctorService.getById(doctorId);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody DoctorCreateUpdateData doctorCreateUpdateData) {
        if (doctorService.save(doctorCreateUpdateData)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Void> update(@PathVariable int doctorId,
                                       @RequestBody DoctorCreateUpdateData doctorCreateUpdateData) {
        if (doctorService.update(doctorId, doctorCreateUpdateData)) {
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

    @GetMapping("/name/{doctorId}")
    public ResponseEntity<String> getName(@PathVariable int doctorId) {
        String doctorName = doctorService.getName(doctorId);
        if (!doctorName.isEmpty()) {
            return new ResponseEntity<>(doctorName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lastName/{doctorFirstName}")
    public ResponseEntity<List<String>> getLastName(@PathVariable String doctorFirstName) {
        List<String> lastNameList = doctorService.getLastName(doctorFirstName);
        if (lastNameList != null) {
            if (!lastNameList.isEmpty()) {
                return new ResponseEntity<>(lastNameList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
