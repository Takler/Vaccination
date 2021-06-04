package hu.demo.vaccination.dto;

import lombok.*;

@Data
public class VaccineCreateData {

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

}
