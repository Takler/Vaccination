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

}
