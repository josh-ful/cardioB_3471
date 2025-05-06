package UserInterface;

import main.DBConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for fetching daily user metrics.
 */
public class DailyMetricDAO {
    private final DataSource ds;
    public DailyMetricDAO(DataSource ds) { this.ds = ds; }

// FROM KIERA'S
//    public static List<Point> fetchMetrics(MetricTypes type) throws SQLException {
//        String sql = "SELECT " + type +" , metric_date FROM daily_metrics WHERE user_id=? ORDER BY metric_date";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement p = c.prepareStatement(sql)) {
//            p.setInt(1, UserController.getUserId());
//            ResultSet r = p.executeQuery();
//            List<Point> out = new ArrayList<>();
//            while (r.next()) {
//                Point dm = new Point();
//                dm.setX(r.getDate("date"));
//                dm.setY(r.getDouble(type.toString()));
//
//                out.add(dm);
//            }
//            return out;
//        }
//    }
    /**
     * Fetches all non-null daily metrics of a specific type for a user, ordered by date.
     * Assumes daily_metrics has columns: user_id, metric_date, weight, sleep, calories, wktduration.
     *
     * @param userId the user ID
     * @param type   the metric type to fetch
     * @return list of DailyMetric objects
     * @throws SQLException if a database access error occurs
     */
    public static List<DailyMetric> fetchMetrics(int userId, MetricTypes type) throws SQLException {
        // Map MetricTypes to actual column names
        String column;
        switch (type) {
            case WEIGHT:      column = "weight";      break;
            case SLEEP:       column = "sleep";       break;
            case CALORIES:    column = "calories";    break;
            case WKTDURATION: column = "wktduration"; break;
            default:
                throw new IllegalArgumentException("Unknown MetricType: " + type);
        }

        String sql = "SELECT metric_date, " + column +
                " FROM daily_metrics" +
                " WHERE user_id = ? AND " + column + " IS NOT NULL" +
                " ORDER BY metric_date";

        List<DailyMetric> metrics = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DailyMetric dm = new DailyMetric();
                    dm.setUserId(userId);
                    dm.setType(type);
                    dm.setValue(rs.getDouble(column));
                    dm.setDate(rs.getDate("metric_date").toLocalDate());
                    metrics.add(dm);
                }
            }
        }
        return metrics;
    }

    /**
     * Returns the most recent value for a given metric.
     */
    public static double fetchSingle(int userId, MetricTypes type) {
        String column = mapTypeToColumn(type);
        String sql = "SELECT " + column +
                " FROM daily_metrics" +
                " WHERE user_id = ? AND " + column + " IS NOT NULL" +
                " ORDER BY metric_date DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Returns the 7-day average for a given metric.
     */
    public static double fetchAverage(int userId, MetricTypes type) {
        String column = mapTypeToColumn(type);
        String sql = "SELECT AVG(" + column + ") AS avg_val" +
                " FROM daily_metrics" +
                " WHERE user_id = ? AND " + column + " IS NOT NULL" +
                " AND metric_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("avg_val");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Updates CurrentUser with the latest stored weight.
     */
    public static void getCurrentWeight(int userId) {
        double val = fetchSingle(userId, MetricTypes.WEIGHT);
        CurrentUser.setCurrentWeight(val);
    }

    /**
     * Updates CurrentUser with the average sleep over the past 7 days.
     */
    public static void getAvgSleep(int userId) {
        double val = fetchAverage(userId, MetricTypes.SLEEP);
        CurrentUser.setAvgSleep(val);
    }

    /**
     * Updates CurrentUser with the average calories over the past 7 days.
     */
    public static void getAvgCalories(int userId) {
        double val = fetchAverage(userId, MetricTypes.CALORIES);
        CurrentUser.setAvgCalories(val);
    }

    /**
     * Updates CurrentUser with the average workout duration over the past 7 days.
     */
    public static void getAvgWorkout(int userId) {
        double val = fetchAverage(userId, MetricTypes.WKTDURATION);
        CurrentUser.setAvgWorkout(val);
    }

    /**
     * Helper: map MetricTypes enum to the actual column in daily_metrics.
     */
    private static String mapTypeToColumn(MetricTypes type) {
        return switch (type) {
            case WEIGHT      -> "weight";
            case SLEEP       -> "sleep";
            case CALORIES    -> "calories";
            case WKTDURATION -> "wktduration";
            default -> throw new IllegalArgumentException("Unknown MetricType: " + type);
        };
    }
}
