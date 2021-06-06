package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.shift.ShiftCreateUpdateData;
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
public class ShiftController {

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    // @GetMapping("/file")
    // @PostMapping("/file")

    @GetMapping
    public ResponseEntity<List<Shift>> findAll() {
        List<Shift> shiftList = shiftService.findAll();
        if (!shiftList.isEmpty()) {
            return new ResponseEntity<>(shiftList, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{shiftId}")
    public ResponseEntity<Shift> getById(@PathVariable int shiftId) {
        Shift shift = shiftService.getById(shiftId);
        if (shift != null) {
            return new ResponseEntity<>(shift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ShiftCreateUpdateData shiftCreateUpdateData) {   // TODO Boolean  B/b ?,   Void V/v ?
        if (shiftService.save(shiftCreateUpdateData)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{shiftId}")
    public ResponseEntity<Void> update(@PathVariable int shiftId,
                                       @RequestBody ShiftCreateUpdateData shiftCreateUpdateData) {
        if (shiftService.update(shiftId, shiftCreateUpdateData)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{shiftId}")
    public ResponseEntity<Void> delete(@PathVariable int shiftId) {
        if (shiftService.delete(shiftId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nameinfo/{shiftId}")
    public ResponseEntity<ShiftNameInfoData> getNameInfo(@PathVariable int shiftId) {
        return new ResponseEntity<>(shiftService.getNameInfo(shiftId), HttpStatus.OK);
    }

    @GetMapping("/info/{shiftId}")
    public ResponseEntity<ShiftInfoData> getInfo(@PathVariable int shiftId) {
        return new ResponseEntity<>(shiftService.getInfo(shiftId), HttpStatus.OK);
    }
}