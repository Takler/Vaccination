package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.dto.vaccination.*;
import hu.demo.vaccination.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccination")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @GetMapping
    public ResponseEntity<List<Vaccination>> getVaccinations() {
        List<Vaccination> vaccinations = vaccinationService.findAll();
        return new ResponseEntity<>(vaccinations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccination> getVaccination(@PathVariable int id) {
        Vaccination vaccination = vaccinationService.getById(id);
        if (vaccination != null) {
            return new ResponseEntity<>(vaccination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createVaccination(@RequestBody VaccinationCreateData data) {
        boolean saveSuccessful = vaccinationService.save(data);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVaccination(@PathVariable int id, @RequestBody VaccinationCreateData data) {
        boolean updateSuccessful = vaccinationService.update(id, data);
        if (updateSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccination(@PathVariable int id) {
        boolean deleteSuccessful = vaccinationService.delete(id);
        if (deleteSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/first")
    @ResponseBody
    public ResponseEntity<CountPercentageData> getFirstVaccinatedPercentage(@RequestParam(name = "min_age", defaultValue = "0") int minAge,
                                                               @RequestParam(name = "max_age", defaultValue = "0") int maxAge,
                                                               @RequestParam(name = "chronic", defaultValue = "false") boolean chronic,
                                                               @RequestParam(name = "pregnant", defaultValue = "false") boolean pregnant) {
        if (minAge > maxAge && maxAge != 0) {
            return new ResponseEntity<>(new CountPercentageData(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(vaccinationService.getFirstVaccinatedData(minAge, maxAge, chronic, pregnant),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/full")
    @ResponseBody
    public ResponseEntity<CountPercentageData> getFullVaccinatedData(@RequestParam(name = "min_age", defaultValue = "0") int minAge,
                                                                    @RequestParam(name = "max_age", defaultValue = "0") int maxAge,
                                                                    @RequestParam(name = "chronic", defaultValue = "false") boolean chronic,
                                                                    @RequestParam(name = "pregnant", defaultValue = "false") boolean pregnant) {
        if (minAge > maxAge && maxAge != 0) {
            return new ResponseEntity<>(new CountPercentageData(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(vaccinationService.getFullVaccinatedData(minAge, maxAge, chronic, pregnant),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<VaccinationInfoData> getVaccinationFullInfo(@PathVariable int id) {
        return new ResponseEntity<>(vaccinationService.getInfo(id), HttpStatus.OK);
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<VaccinationNameInfoData> getVaccinationNameInfo(@PathVariable int id) {
        return new ResponseEntity<>(vaccinationService.getNameInfo(id), HttpStatus.OK);
    }

    @GetMapping("/vaccine")
    public ResponseEntity<List<AggregatedFieldData>> getVaccinatedPerVaccine() {
        return new ResponseEntity<>(vaccinationService.getVaccinatedPerVaccine(), HttpStatus.OK);
    }

    @GetMapping("/period")
    public ResponseEntity<Integer> getNumberOfVaccinationsForPeriod(@RequestParam LocalDate start,
                                                                    @RequestParam LocalDate end) {
        return new ResponseEntity<>(vaccinationService.getNumberOfVaccinationsForPeriod(start, end), HttpStatus.OK);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<VaccinationInfoData>> getVaccinationInfoByPatient(@PathVariable int id) {
        return new ResponseEntity<>(vaccinationService.getVaccinationInfoByPatient(id), HttpStatus.OK);
    }
}
