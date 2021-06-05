package hu.demo.vaccination.domain;

import hu.demo.vaccination.dto.VaccineCreateData;
import lombok.*;

import java.util.Objects;

@Data
public class Vaccine {

    private int id;
    private String name;
    private String type;
    private int storageTemperature;
    private int ageLimitMin;
    private int ageLimitMax;
    private int shotsNeeded;
    private int daysUntilNextShot;
    private int nextShotId;
    private int fullyVaccinatedTimePeriod;
    private boolean applicable;
    private boolean applicableForPregnant;
    private boolean applicableForChronic;

    public Vaccine() {}

    public Vaccine(int id, String name, String type, int storageTemperature, int ageLimitMin, int ageLimitMax,
                   int shotsNeeded, int daysUntilNextShot, int nextShotId, int fullyVaccinatedTimePeriod,
                   boolean applicable, boolean applicableForPregnant, boolean applicableForChronic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.storageTemperature = storageTemperature;
        this.ageLimitMin = ageLimitMin;
        this.ageLimitMax = ageLimitMax;
        this.shotsNeeded = shotsNeeded;
        this.daysUntilNextShot = daysUntilNextShot;
        this.nextShotId = nextShotId;
        this.fullyVaccinatedTimePeriod = fullyVaccinatedTimePeriod;
        this.applicable = applicable;
        this.applicableForPregnant = applicableForPregnant;
        this.applicableForChronic = applicableForChronic;
    }

    public Vaccine(int id, VaccineCreateData vaccineCreateData) {
        this.id = id;
        this.name = vaccineCreateData.getName();
        this.type = vaccineCreateData.getType();
        this.storageTemperature = vaccineCreateData.getStorageTemperature();
        this.ageLimitMin = vaccineCreateData.getAgeLimitMin();
        this.ageLimitMax = vaccineCreateData.getAgeLimitMax();
        this.shotsNeeded = vaccineCreateData.getShotsNeeded();
        this.daysUntilNextShot = vaccineCreateData.getDaysUntilNextShot();
        this.nextShotId = vaccineCreateData.getNextShotId();
        this.fullyVaccinatedTimePeriod = vaccineCreateData.getFullyVaccinatedTimePeriod();
        this.applicable = vaccineCreateData.isApplicable();
        this.applicableForPregnant = vaccineCreateData.isApplicableForPregnant();
        this.applicableForChronic = vaccineCreateData.isApplicableForChronic();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return id == vaccine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
