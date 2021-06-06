package hu.demo.vaccination.dto.shift;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShiftCreateUpdateData {   //TODO Miért külön utaztatjuk at ID-t update-nál?

    private int centerId;
    private int doctorId;
    private Timestamp start;    //TODO dátumformátum
    private Timestamp end;

    public ShiftCreateUpdateData() {
    }
}

