package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Doctor {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String telephoneNumber;
    @JsonIgnore
    private boolean deleted;

    public Doctor() {
    }
}
