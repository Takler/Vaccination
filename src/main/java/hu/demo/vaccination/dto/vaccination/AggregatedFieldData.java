package hu.demo.vaccination.dto.vaccination;

import lombok.Data;

@Data
public class AggregatedFieldData {

    private String name;
    private CountPercentageData countPercentageData;

    public AggregatedFieldData() {}

    public AggregatedFieldData(String name, CountPercentageData countPercentageData) {
        this.name = name;
        this.countPercentageData = countPercentageData;
    }

}
