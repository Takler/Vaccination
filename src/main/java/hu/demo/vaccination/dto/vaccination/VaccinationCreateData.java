package hu.demo.vaccination.dto.vaccination;

import lombok.*;

import java.time.LocalDate;

@Data
public class VaccinationCreateData {

    private int vaccineId;
    private int patientId;
    private int shiftId;
    private LocalDate date;

    public VaccinationCreateData() {
    }

    public VaccinationCreateData(int vaccineId, int patientId, int shiftId, LocalDate date) {
        this.vaccineId = vaccineId;
        this.patientId = patientId;
        this.shiftId = shiftId;
        this.date = date;
    }
}
