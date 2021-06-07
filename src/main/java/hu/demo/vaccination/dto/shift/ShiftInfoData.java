package hu.demo.vaccination.dto.shift;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Doctor;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShiftInfoData {

    private int id;
    private Timestamp start;
    private Timestamp end;
    private Doctor doctor;
    private Center center;

    public ShiftInfoData() {
    }
}
