package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Reservation {

    private int id;
    private int patientId;
    private int centerId;
    private int vaccineId;
    private LocalDate registration;
    private LocalDate nextShot;
    @JsonIgnore
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    public LocalDate getNextShot() {
        return nextShot;
    }

    public void setNextShot(LocalDate nextShot) {
        this.nextShot = nextShot;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
