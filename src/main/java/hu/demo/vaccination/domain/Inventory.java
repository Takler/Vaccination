package hu.demo.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Inventory {
    private int id;
    private int centerId;
    private int vaccineId;
    private int amount;
    @JsonIgnore
    private boolean deleted;

}
