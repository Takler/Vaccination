package hu.demo.vaccination.dto.vaccination;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.shift.ShiftInfoData;

import java.time.LocalDate;

public class VaccinationInfoData {

    private int id;
    private Vaccine vaccine;
    private Patient patient;
    private ShiftInfoData shift;
    private LocalDate date;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ShiftInfoData getShift() {
        return shift;
    }

    public void setShift(ShiftInfoData shift) {
        this.shift = shift;
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
