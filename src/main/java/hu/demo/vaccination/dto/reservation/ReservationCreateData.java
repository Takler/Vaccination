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

}
