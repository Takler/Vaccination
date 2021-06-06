package hu.demo.vaccination.dto.shift;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShiftNameInfoData {

    private int id;
    private Timestamp start;
    private Timestamp end;
    private boolean deleted;
    private String doctorFirstName;
    private String doctorLastName;
    private String centerName;
    //private String city;

    public ShiftNameInfoData() {
    }
}
