package hu.demo.vaccination.dto.shift;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Doctor;

import java.sql.Timestamp;

public class ShiftInfoData {
    private int id;
    private Timestamp start;
    private Timestamp end;
    private boolean deleted;
    private Doctor doctor;
    private Center center;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
