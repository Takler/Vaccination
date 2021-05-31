package hu.demo.vaccination.dto.vaccination;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.domain.Vaccine;

import java.time.LocalDate;

public class VaccinationInfoData {

    private int id;
    private Vaccine vaccine;
    private Patient patient;
    private Shift shift;
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
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
