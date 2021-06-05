package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class Vaccination {

    private int id;
    private int vaccineId;
    private int patientId;
    private int shiftId;
    private LocalDate date;
    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccination that = (Vaccination) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
