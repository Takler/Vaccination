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

    public PatientCreateData() {
    }

    public PatientCreateData(int id, String firstName, String lastName, String mothersName, String gender,
                             LocalDate dateOfBirth, String email, String city, String zipCode, String address,
                             String telephoneNumber, boolean pregnant, boolean underlyingMedicalCondition) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mothersName = mothersName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.pregnant = pregnant;
        this.underlyingMedicalCondition = underlyingMedicalCondition;
    }
}
