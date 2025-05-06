package UserInterface;

import java.time.LocalDate;

public class DailyMetric {
    private int userId;
    private MetricTypes type;         // enum { WEIGHT, SLEEP, CALORIES, WORKOUT }
    private double value;
    private LocalDate date;

    DailyMetric(){}

    DailyMetric(int userId, MetricTypes type, double value) {
        this.userId = userId;
        this.type = type;
        this.value = value;
        this.date = LocalDate.now();
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public MetricTypes getType() {
        return type;
    }

    public void setType(MetricTypes type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
