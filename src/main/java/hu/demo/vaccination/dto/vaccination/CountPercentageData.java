package hu.demo.vaccination.dto.vaccination;

import lombok.Data;

@Data
public class CountPercentageData {

    private int count;
    private double percentage;

    public CountPercentageData() {}

    public CountPercentageData(int count, double percentage) {
        this.count = count;
        this.percentage = percentage;
    }

}
