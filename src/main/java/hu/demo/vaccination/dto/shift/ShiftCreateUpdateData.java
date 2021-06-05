package hu.demo.vaccination.dto.shift;

import java.sql.Timestamp;

public class ShiftCreateUpdateData {   //TODO Miért külön utaztatjuk at ID-t update-nál?

    private int centerId;
    private int doctorId;
    private Timestamp start;    //TODO dátumformátum
    private Timestamp end;

    public ShiftCreateUpdateData() {
    }

    //TODO @data
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
}

