package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Shift {

    private int id;
    private int centerId;
    private int doctorId;
    private Timestamp start;  //TODO dátumformátum
    private Timestamp end;
    @JsonIgnore
    private boolean deleted;

    public Shift() {
    }

    // TODO @DATA
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
