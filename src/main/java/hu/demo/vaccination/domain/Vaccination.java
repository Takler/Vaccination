package hu.demo.vaccination.domain;

import java.time.LocalDate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
