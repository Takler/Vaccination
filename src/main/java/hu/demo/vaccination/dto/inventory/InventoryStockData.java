package hu.demo.vaccination.dto.inventory;

import lombok.Data;

@Data
public class InventoryStockData {
    private String vaccineName;
    private int amount;

}
