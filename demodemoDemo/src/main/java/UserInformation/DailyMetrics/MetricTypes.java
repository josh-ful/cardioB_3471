package UserInformation.DailyMetrics;

public enum MetricTypes {
    WEIGHT("weight"),
    SLEEP("sleep"),
    CALORIES("calories"),
    WKTDURATION("wktduration"),;

    public String getName() {
        return name;
    }

    private final String name;

    MetricTypes(String name) {
        this.name = name;
    }
}
