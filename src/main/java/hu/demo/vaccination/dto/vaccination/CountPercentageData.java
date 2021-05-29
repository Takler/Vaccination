package hu.demo.vaccination.dto.vaccination;

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
}
