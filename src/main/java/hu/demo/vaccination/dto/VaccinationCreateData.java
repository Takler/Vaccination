package hu.demo.vaccination.dto;

import java.time.LocalDate;

public class VaccinationCreateData {

    private int vaccineId;
    private int patientId;
    private int shiftId;
    private LocalDate date;
    private boolean deleted;

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
