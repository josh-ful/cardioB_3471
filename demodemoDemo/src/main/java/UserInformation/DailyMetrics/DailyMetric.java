package UserInformation.DailyMetrics;

import java.time.LocalDate;
import java.util.Date;
/*
 * this class represents a DailyMetric object
 * containing information about DailyMetrics
 */
public class DailyMetric {
    private Double w;
    private Double s;
    private Double c;
    private Double wkt;
    private LocalDate date;
    /**
     * constructs a DailyMetric object
     *
     * @param w Double weight
     * @param s Double hours of sleep
     * @param c Double calories consumed
     * @param wkt Double total time of workout
     * @param date LocalDate date of dailyMetric
     */
    public DailyMetric(Double w, Double s, Double c, Double wkt, LocalDate date){
        this.w = w;
        this.s = s;
        this.c = c;
        this.wkt = wkt;
        this.date = date;
    }
    /**
     * gets total calories for this daily metric
     *
     * @return Double total calories
     */
    public Double getC() {
        return c;
    }
    /**
     * sets total calories for this daily metric
     *
     * @param c Double total calories
     */
    public void setC(Double c) {
        this.c = c;
    }
    /**
     * gets total sleep for this daily metric
     *
     * @return Double sleep time
     */
    public Double getS() {
        return s;
    }
    /**
     * gets total sleep for this daily metric
     *
     * @param s Double sleep time
     */
    public void setS(Double s) {
        this.s = s;
    }
    /**
     * gets weight for this daily metric
     *
     * @return Double weight
     */
    public Double getW() {
        return w;
    }
    /**
     * sets weight for this daily metric
     *
     * @param w Double weight
     */
    public void setW(Double w) {
        this.w = w;
    }
    /**
     * gets total time of workout for this daily metric
     *
     * @return Double duration of workout
     */
    public Double getWkt() {
        return wkt;
    }
    /**
     * sets total time of workout for this daily metric
     *
     * @param wkt Double time of workout
     */
    public void setWkt(Double wkt) {
        this.wkt = wkt;
    }
    /**
     * sets date of this daily metric
     *
     * @return String date of daily metric
     */
    public String getDate() {
        return date.toString();
    }
    /**
     * sets date of this daily metric
     *
     * @param date LocalDate date of daily metric
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
