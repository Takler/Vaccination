package hu.demo.vaccination.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class VaccinationCreateData {

    private int vaccineId;
    private int patientId;
    private int shiftId;
    private LocalDate date;

}
