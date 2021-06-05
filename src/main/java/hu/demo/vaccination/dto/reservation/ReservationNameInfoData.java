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

    public ReservationNameInfoData() {
    }

    public ReservationNameInfoData(int id, String patientName, String centerName, String vaccineName,
                                   LocalDate registration, LocalDate nextShot) {
        this.id = id;
        this.patientName = patientName;
        this.centerName = centerName;
        this.vaccineName = vaccineName;
        this.registration = registration;
        this.nextShot = nextShot;
    }
}
