package hu.demo.vaccination.controller;

import hu.demo.vaccination.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccination")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @GetMapping("/first")
    @ResponseBody
    public ResponseEntity<Double> getFirstVaccinatedPercentage(@RequestParam(name = "min_age", defaultValue = "0") int minAge,
                                                               @RequestParam(name = "max_age", defaultValue = "0") int maxAge,
                                                               @RequestParam(name = "chronic", defaultValue = "false") boolean chronic,
                                                               @RequestParam(name = "pregnant", defaultValue = "false") boolean pregnant) {
        if (minAge > maxAge && maxAge != 0) {
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(vaccinationService.getFirstVaccinatedPercentage(minAge, maxAge, chronic, pregnant),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/full")
    @ResponseBody
    public ResponseEntity<Double> getFullVaccinatedPercentage(@RequestParam(name = "min_age", defaultValue = "0") int minAge,
                                                               @RequestParam(name = "max_age", defaultValue = "0") int maxAge,
                                                               @RequestParam(name = "chronic", defaultValue = "false") boolean chronic,
                                                               @RequestParam(name = "pregnant", defaultValue = "false") boolean pregnant) {
        if (minAge > maxAge && maxAge != 0) {
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(vaccinationService.getFirstVaccinatedPercentage(minAge, maxAge, chronic, pregnant),
                    HttpStatus.OK);
        }
    }
}
