package hu.demo.vaccination.dto.inventory;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Vaccine;
import lombok.Data;

@Data
public class InventoryInfoData {
    private int id;
    private Center center;
    private Vaccine vaccine;
    private int amount;

}
