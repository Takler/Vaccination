package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;

@Data
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
}
