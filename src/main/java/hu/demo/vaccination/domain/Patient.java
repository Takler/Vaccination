package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {

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
    @JsonIgnore
    private boolean deleted;

    public String toCsvFormat() {
        return  id + ";" + firstName + ";" + lastName + ";" + mothersName + ";" + gender + ";" + dateOfBirth +
                ";" + email + ";" + city + ";" + zipCode + ";" + address + ";" + telephoneNumber +
                ";" + pregnant + ";" + underlyingMedicalCondition;
    }
}
