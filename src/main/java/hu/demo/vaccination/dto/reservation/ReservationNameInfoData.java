package hu.demo.vaccination.dto.reservation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationNameInfoData {

    private int id;
    private String patientName;
    private String centerName;
    private String vaccineName;
    private LocalDate registration;
    private LocalDate nextShot;

}
