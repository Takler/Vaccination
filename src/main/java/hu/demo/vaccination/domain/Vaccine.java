package hu.demo.vaccination.domain;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isApplicable() {
        return applicable;
    }

    public void setApplicable(boolean applicable) {
        this.applicable = applicable;
    }

    public boolean isApplicableForPregnant() {
        return applicableForPregnant;
    }

    public void setApplicableForPregnant(boolean applicableForPregnant) {
        this.applicableForPregnant = applicableForPregnant;
    }

    public boolean isApplicableForChronic() {
        return applicableForChronic;
    }

    public void setApplicableForChronic(boolean applicableForChronic) {
        this.applicableForChronic = applicableForChronic;
    }
}
