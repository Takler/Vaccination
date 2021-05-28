package hu.demo.vaccination.controller;

import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ShiftInfoData> getShiftInfo(int shiftId){
        return new ResponseEntity<>(shiftService.getShiftInfo(shiftId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getShiftList() {
        return new ResponseEntity<>("info", HttpStatus.OK);
    }
}
