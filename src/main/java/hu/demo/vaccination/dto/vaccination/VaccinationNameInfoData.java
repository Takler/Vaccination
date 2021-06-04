package hu.demo.vaccination.dto.vaccination;

import lombok.*;

import java.time.LocalDate;

@Data
public class VaccinationNameInfoData {

    private int id;
    private String vaccineName;
    private String patientName;
    private String centerName;
    private String doctorName;
    private LocalDate date;
    private boolean deleted;

}
