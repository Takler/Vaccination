package hu.demo.vaccination.dto;

import lombok.Data;

@Data
public class CenterCreateData {
    private String name;
    private String city;
    private String email;
    private String telephoneNumber;
    private int dailyCapacity;

}
