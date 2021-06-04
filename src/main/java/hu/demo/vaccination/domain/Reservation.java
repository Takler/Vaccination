package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {

    private int id;
    private int patientId;
    private int centerId;
    private int vaccineId;
    private LocalDate registration;
    private LocalDate nextShot;
    @JsonIgnore
    private boolean deleted;

    public Reservation() {
    }

    public Reservation(int id, int patientId, int centerId, int vaccineId, LocalDate registration,
                       LocalDate nextShot, boolean deleted) {
        this.id = id;
        this.patientId = patientId;
        this.centerId = centerId;
        this.vaccineId = vaccineId;
        this.registration = registration;
        this.nextShot = nextShot;
        this.deleted = deleted;
    }
}
