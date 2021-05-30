package hu.demo.vaccination.dto.vaccination;

import java.util.Objects;

public class AggregatedFieldData {

    private String name;
    private CountPercentageData countPercentageData;

    public AggregatedFieldData() {}

    public AggregatedFieldData(String name, CountPercentageData countPercentageData) {
        this.name = name;
        this.countPercentageData = countPercentageData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountPercentageData getCountPercentageData() {
        return countPercentageData;
    }

    public void setCountPercentageData(CountPercentageData countPercentageData) {
        this.countPercentageData = countPercentageData;
    }

    @Override
    public String toString() {
        return "AggregatedFieldData{" +
                "name='" + name + '\'' +
                ", countPercentageData=" + countPercentageData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregatedFieldData that = (AggregatedFieldData) o;
        return name.equals(that.name) &&
                countPercentageData.equals(that.countPercentageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countPercentageData);
    }
}
