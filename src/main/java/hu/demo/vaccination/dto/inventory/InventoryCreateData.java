package hu.demo.vaccination.dto.inventory;

import lombok.Data;

@Data
public class InventoryCreateData {
    private int centerId;
    private int vaccineId;
    private int amount;

    public InventoryCreateData() {
    }

    public InventoryCreateData(int centerId, int vaccineId, int amount) {
        this.centerId = centerId;
        this.vaccineId = vaccineId;
        this.amount = amount;
    }
}
