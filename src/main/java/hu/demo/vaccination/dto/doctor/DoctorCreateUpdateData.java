package hu.demo.vaccination.dto.doctor;

import lombok.Data;

@Data
public class DoctorCreateUpdateData {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String telephoneNumber;

    public DoctorCreateUpdateData() {
    }
}
