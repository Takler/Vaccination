package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.shift.ShiftCreateData;
import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.dto.shift.ShiftNameInfoData;
import hu.demo.vaccination.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {     // CRUD interface-t implementálni

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping("/{shiftId}")
    public ResponseEntity<Shift> getById(int shiftId) {
        return new ResponseEntity<>(shiftService.getById(shiftId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Shift>> findAll() {
        return new ResponseEntity<>(shiftService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody ShiftCreateData shiftCreateData) {   // B?
        return new ResponseEntity<>(shiftService.save(shiftCreateData), HttpStatus.OK);
    }

    @GetMapping("/nameinfo/{shiftId}")
    public ResponseEntity<ShiftNameInfoData> getNameInfo(@PathVariable int shiftId) {
        return new ResponseEntity<>(shiftService.getNameInfo(shiftId), HttpStatus.OK);
    }

    @GetMapping("/info/{shiftId}")
    public ResponseEntity<ShiftInfoData> getInfo(@PathVariable int shiftId) {
        return new ResponseEntity<>(shiftService.getInfo(shiftId), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<String> getShiftList() {
        return new ResponseEntity<>("info", HttpStatus.OK);
    }
}
