package hu.demo.vaccination.dto.vaccination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccinationNameInfoData {

    private int id;
    private String vaccineName;
    private String patientName;
    private String centerName;
    private String doctorName;
    private LocalDate date;
    @JsonIgnore
    private boolean deleted;

}
