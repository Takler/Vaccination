package hu.demo.vaccination.dto.reservation;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationInfoData {

    private int id;
    private Patient patient;
    private Center center;
    private Vaccine vaccine;
    private LocalDate registration;
    private LocalDate nextShot;

}
