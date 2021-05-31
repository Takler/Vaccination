package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.CenterCreateData;
import hu.demo.vaccination.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/center")
public class CenterController {
    private final CenterService centerService;

    @Autowired
    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping
    public ResponseEntity<List<Center>> getCenters() {
        List<Center> centers = centerService.findAll();
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenter(@PathVariable int id) {
        Center center = centerService.getById(id);
        if (center != null) {
            return new ResponseEntity<>(center, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createCenter(@RequestBody CenterCreateData data) {
        boolean saveSuccessful = centerService.save(data);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCenter(@PathVariable int id, @RequestBody CenterCreateData data) {
        boolean updateSuccessful = centerService.update(id, data);
        if (updateSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable int id) {
        boolean deleteSuccessful = centerService.delete(id);
        if (deleteSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
