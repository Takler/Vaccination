package hu.demo.vaccination.dto.vaccination;

import lombok.Data;

import java.util.Objects;

@Data
public class CountPercentageData {

    private int count;
    private double percentage;

    public CountPercentageData() {}

    public CountPercentageData(int count, double percentage) {
        this.count = count;
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountPercentageData that = (CountPercentageData) o;
        return count == that.count && Double.compare(that.percentage, percentage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, percentage);
    }
}
