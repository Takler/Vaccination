package hu.demo.vaccination.dto.vaccination;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.shift.ShiftInfoData;
import lombok.*;

import java.time.LocalDate;

@Data
public class VaccinationInfoData {

    private int id;
    private Vaccine vaccine;
    private Patient patient;
    private ShiftInfoData shift;
    private LocalDate date;
    private boolean deleted;

}
