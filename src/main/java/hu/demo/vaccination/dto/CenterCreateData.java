package hu.demo.vaccination.dto;

import lombok.Data;

@Data
public class CenterCreateData {
    private String name;
    private String city;
    private String email;
    private String telephoneNumber;
    private int dailyCapacity;

    public CenterCreateData() {
    }

    public CenterCreateData(String name, String city, String email, String telephoneNumber, int dailyCapacity) {
        this.name = name;
        this.city = city;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.dailyCapacity = dailyCapacity;
    }
}
