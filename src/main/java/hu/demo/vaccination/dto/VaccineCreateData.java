package hu.demo.vaccination.dto;

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
    private boolean isApplicableForPregnant;
    private boolean isApplicableForChronic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(int storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    public int getAgeLimitMin() {
        return ageLimitMin;
    }

    public void setAgeLimitMin(int ageLimitMin) {
        this.ageLimitMin = ageLimitMin;
    }

    public int getAgeLimitMax() {
        return ageLimitMax;
    }

    public void setAgeLimitMax(int ageLimitMax) {
        this.ageLimitMax = ageLimitMax;
    }

    public int getShotsNeeded() {
        return shotsNeeded;
    }

    public void setShotsNeeded(int shotsNeeded) {
        this.shotsNeeded = shotsNeeded;
    }

    public int getDaysUntilNextShot() {
        return daysUntilNextShot;
    }

    public void setDaysUntilNextShot(int daysUntilNextShot) {
        this.daysUntilNextShot = daysUntilNextShot;
    }

    public int getNextShotId() {
        return nextShotId;
    }

    public void setNextShotId(int nextShotId) {
        this.nextShotId = nextShotId;
    }

    public int getFullyVaccinatedTimePeriod() {
        return fullyVaccinatedTimePeriod;
    }

    public void setFullyVaccinatedTimePeriod(int fullyVaccinatedTimePeriod) {
        this.fullyVaccinatedTimePeriod = fullyVaccinatedTimePeriod;
    }

    public boolean isApplicableForPregnant() {
        return isApplicableForPregnant;
    }

    public void setApplicableForPregnant(boolean applicableForPregnant) {
        isApplicableForPregnant = applicableForPregnant;
    }

    public boolean isApplicableForChronic() {
        return isApplicableForChronic;
    }

    public void setApplicableForChronic(boolean applicableForChronic) {
        isApplicableForChronic = applicableForChronic;
    }
}