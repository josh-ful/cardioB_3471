package UserInformation.DailyMetrics;

import java.time.LocalDate;
import java.util.Date;

public class DailyMetric {
    private Double w;
    private Double s;
    private Double c;
    private Double wkt;
    private LocalDate date;

    DailyMetric(){}

    public DailyMetric(Double w, Double s, Double c, Double wkt, LocalDate date){
        this.w = w;
        this.s = s;
        this.c = c;
        this.wkt = wkt;
        this.date = date;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getS() {
        return s;
    }

    public void setS(Double s) {
        this.s = s;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getWkt() {
        return wkt;
    }

    public void setWkt(Double wkt) {
        this.wkt = wkt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
