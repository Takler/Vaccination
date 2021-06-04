package hu.demo.vaccination.dto.reservation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationCreateData {

    private int patientId;
    private int centerId;
    private int vaccineId;
    private LocalDate registration;
    private LocalDate nextShot;

    public ReservationCreateData() {
    }

    public ReservationCreateData(int patientId, int centerId, int vaccineId, LocalDate registration, LocalDate nextShot) {
        this.patientId = patientId;
        this.centerId = centerId;
        this.vaccineId = vaccineId;
        this.registration = registration;
        this.nextShot = nextShot;
    }
}
