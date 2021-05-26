package hu.demo.vaccination.dto.reservation;

import java.time.LocalDate;

public class PatientReservationData {

    private int patientId;
    private String patientName;
    private String centerName;
    private String vaccineName;
    private LocalDate registration;
    private LocalDate nextShot;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
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
