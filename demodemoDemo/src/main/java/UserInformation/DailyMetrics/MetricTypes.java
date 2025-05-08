package UserInformation.DailyMetrics;
/*
 * this enum represents a MetricType type enum and its information
 */
public enum MetricTypes {
    WEIGHT("weight"),
    SLEEP("sleep"),
    CALORIES("calories"),
    WKTDURATION("wktduration"),;
    /**
     * gets name/type of daily metric
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    private final String name;
    /**
     * constructs MetricTypes object
     *
     * @param name String name of type of daily metric
     */
    MetricTypes(String name) {
        this.name = name;
    }
}
