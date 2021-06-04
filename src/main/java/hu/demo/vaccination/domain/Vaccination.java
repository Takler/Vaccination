package hu.demo.vaccination.domain;

import lombok.*;

import java.time.LocalDate;

@Data
public class Vaccination {

    private int id;
    private int vaccineId;
    private int patientId;
    private int shiftId;
    private LocalDate date;
    private boolean deleted;

    public Vaccination() {}

    public Vaccination(int id, int vaccineId, int patientId, int shiftId, LocalDate date, boolean deleted) {
        this.id = id;
        this.vaccineId = vaccineId;
        this.patientId = patientId;
        this.shiftId = shiftId;
        this.date = date;
        this.deleted = deleted;
    }

}
