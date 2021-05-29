package hu.demo.vaccination.dto.vaccination;

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
}
