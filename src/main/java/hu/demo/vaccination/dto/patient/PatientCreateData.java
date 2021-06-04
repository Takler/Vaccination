package hu.demo.vaccination.dto.patient;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientCreateData {

    private int id;
    private String firstName;
    private String lastName;
    private String mothersName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String city;
    private String zipCode;
    private String address;
    private String telephoneNumber;
    private boolean pregnant;
    private boolean underlyingMedicalCondition;

}
