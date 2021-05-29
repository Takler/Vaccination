package hu.demo.vaccination.dto.reservation;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;

import java.time.LocalDate;

public class ReservationInfoData {

    private int id;
    private Patient patient;
    private Center center;
    private Vaccine vaccine;
    private LocalDate registration;
    private LocalDate nextShot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
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
}
