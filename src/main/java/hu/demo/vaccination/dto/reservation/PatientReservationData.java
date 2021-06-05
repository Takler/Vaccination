package hu.demo.vaccination.dto.reservation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientReservationData {

    private int reservationId;
    private int patientId;
    private String patientName;
    private String centerName;
    private String vaccineName;
    private LocalDate registration;
    private LocalDate nextShot;

    public PatientReservationData() {
    }

    public PatientReservationData(int reservationId, int patientId, String patientName, String centerName, String vaccineName, LocalDate registration, LocalDate nextShot) {
        this.reservationId = reservationId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.centerName = centerName;
        this.vaccineName = vaccineName;
        this.registration = registration;
        this.nextShot = nextShot;
    }
}
