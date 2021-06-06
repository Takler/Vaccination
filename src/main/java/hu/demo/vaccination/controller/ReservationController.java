package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
import hu.demo.vaccination.dto.reservation.ReservationNameInfoData;
import hu.demo.vaccination.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/patientid/{patientId}")
    public ResponseEntity<PatientReservationData> getPatientReservation(@PathVariable int patientId) {
        PatientReservationData patientReservation = reservationService.getPatientReservation(patientId);
        if (patientReservation != null) {
            return new ResponseEntity<>(patientReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ReservationInfoData> getInfo(@PathVariable int id) {
        ReservationInfoData info = reservationService.getInfo(id);
        if (info != null) {
            return new ResponseEntity<>(info, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nameinfo/{id}")
    public ResponseEntity<ReservationNameInfoData> getNameInfo(@PathVariable int id) {
        ReservationNameInfoData nameInfo = reservationService.getNameInfo(id);
        if (nameInfo != null) {
            return new ResponseEntity<>(nameInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        List<Reservation> reservations = reservationService.findAll();
        if (!reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable int id) {
        Reservation reservation = reservationService.getById(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ReservationCreateData data) {
        if (reservationService.save(data)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody ReservationCreateData data) {
        if (reservationService.update(id, data)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (reservationService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
