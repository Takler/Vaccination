package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Center {
    private int id;
    private String name;
    private String city;
    private String email;
    private String telephoneNumber;
    private int dailyCapacity;
    @JsonIgnore
    private boolean deleted;

    public Center() {
    }

    public Center(int id, String name, String city, String email, String telephoneNumber, int dailyCapacity, boolean deleted) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.dailyCapacity = dailyCapacity;
        this.deleted = deleted;
    }
}
