package hu.demo.vaccination.dto.inventory;

import lombok.Data;

@Data
public class InventoryNameInfoData {
    private int id;
    private String centerName;
    private String vaccineName;
    private int amount;

}
