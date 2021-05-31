package hu.demo.vaccination.dto.inventory;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Vaccine;

public class InventoryInfoData {
    private int id;
    private Center center;
    private Vaccine vaccine;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
