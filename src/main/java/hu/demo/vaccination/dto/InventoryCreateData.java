package hu.demo.vaccination.dto;

import lombok.Data;

@Data
public class InventoryCreateData {
    private int centerId;
    private int vaccineId;
    private int amount;

}
