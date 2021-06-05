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

}
