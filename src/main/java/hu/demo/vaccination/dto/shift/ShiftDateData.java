package hu.demo.vaccination.dto.shift;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShiftDateData {  //TODO ezt az egészet átnézni

    private Timestamp start;
    private Timestamp end;

    public ShiftDateData() {
    }
}
