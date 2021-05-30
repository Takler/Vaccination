package hu.demo.vaccination.dto.vaccination;

import java.util.Objects;

public class CountPercentageData {

    private int count;
    private double percentage;

    public CountPercentageData() {}

    public CountPercentageData(int count, double percentage) {
        this.count = count;
        this.percentage = percentage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountPercentageData{" +
                "count=" + count +
                ", percentage=" + percentage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountPercentageData that = (CountPercentageData) o;
        return count == that.count &&
                Double.compare(that.percentage, percentage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, percentage);
    }
}
