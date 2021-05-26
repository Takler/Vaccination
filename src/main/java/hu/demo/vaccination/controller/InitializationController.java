package hu.demo.vaccination.controller;

import hu.demo.vaccination.service.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/initialization")
public class InitializationController {

    private final InitializationService initializationService;

    @Autowired
    public InitializationController(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    @GetMapping
    public ResponseEntity<Void> initialization() {
        initializationService.initialization();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
