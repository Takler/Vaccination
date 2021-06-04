package hu.demo.vaccination.domain;

import lombok.*;

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

}
