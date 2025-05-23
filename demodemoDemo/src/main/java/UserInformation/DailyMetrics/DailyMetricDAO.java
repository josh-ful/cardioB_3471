package UserInformation.DailyMetrics;

import Controller.UserController;
import UserInformation.CurrentUser;
import UserInformation.DailyMetrics.MetricTypes;
import UserInterface.graphs.Point;
import com.mysql.cj.conf.DatabaseUrlContainer;
import main.DBConnection;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for fetching daily user metrics.
 */
public class DailyMetricDAO {
    private final DataSource ds;

    public DailyMetricDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Fetches all non-null daily metrics of a specific type for a user, ordered by date.
     * Assumes daily_metrics has columns: user_id, metric_date, weight, sleep, calories, wktduration.
     *
     * @param type the metric type to fetch
     * @return list of DailyMetric objects
     * @throws SQLException if a database access error occurs
     */
    public static List<Point> fetchAllMetrics(MetricTypes type) {

        String sql = "SELECT metric_date, " + type.getName() + " FROM daily_metrics" +
                " WHERE user_id = ? AND " + type.getName() + " IS NOT NULL" + " ORDER BY metric_date";
        List<Point> metrics = new ArrayList<>();


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, UserController.getUserId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Point dm = new Point();
                    dm.setX(rs.getDate("metric_date"));
                    dm.setY(rs.getDouble(type.toString()));
                    metrics.add(dm);
                }
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Caught error");
        }
        return metrics;
    }

    private static final String GET_LATEST = ""
            + "SELECT ? "
            + "  FROM daily_metrics "
            + " WHERE user_id = ? "
            + " ORDER BY metric_date DESC "
            + " LIMIT 1";

    private static final String GET_AVG = ""
            + "SELECT AVG(?) AS avg_val "
            + "  FROM daily_metrics "
            + " WHERE user_id = ? "
            + "   AND metric_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";


    private static String mapTypeToColumn(MetricTypes mt) {
        return switch (mt) {
            case WEIGHT      -> "weight";
            case SLEEP       -> "sleep";
            case CALORIES    -> "calories";
            case WKTDURATION -> "wktduration";
        };
    }

    /**
     * Returns the most recent value for a given metric.
     *
     * @param mt MetricTypes
     */
    private static double fetchSingle(MetricTypes mt) throws SQLException {
        String column = mapTypeToColumn(mt);
        String sql = String.format("""
        SELECT %1$s
          FROM daily_metrics
         WHERE user_id = ?
           AND %1$s IS NOT NULL
         ORDER BY metric_date DESC
         LIMIT 1
        """,
                column  // both %1$s instances become your column name
        );

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, UserController.getUserId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // pull the real numeric column
                    return rs.getDouble(column);
                }
            }
        }
        return 0.0;
    }
    /**
     * Returns the 7-day average for a given metric.
     *
     * @param mt MetricTypes
     */
    private static double fetchAverage(MetricTypes mt) throws SQLException {
        String column = mapTypeToColumn(mt);
        // note the two “%s” placeholders
        String sql = String.format("""
        SELECT AVG(%s) AS avg_val
          FROM daily_metrics
         WHERE user_id = ?
           AND %s IS NOT NULL
           AND metric_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
        """,
                column,   // first %s → column in SELECT
                column    // second %s → column in WHERE
        );

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, UserController.getUserId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("avg_val");
                }
            }
        }
        return 0.0;
    }
    /**
     * Updates CurrentUser with the latest stored weight.
     *
     */
    public static void getCurrentWeight() throws SQLException {
        double val = fetchSingle(MetricTypes.WEIGHT);
        CurrentUser.setCurrentWeight(val);
    }
    /**
     * Updates CurrentUser with the average sleep over the past 7 days.
     *
     */
    public static void getAvgSleep() throws SQLException {
        CurrentUser.setAvgSleep(0.0);
        double val = fetchAverage(MetricTypes.SLEEP);
        CurrentUser.setAvgSleep(val);
    }
    /**
     * Updates CurrentUser with the average calories over the past 7 days.
     *
     */
    public static void getAvgCalories() throws SQLException {
        CurrentUser.setAvgCalories(0.0);
        double val = fetchAverage(MetricTypes.CALORIES);
        CurrentUser.setAvgCalories(val);
    }
    /**
     * Updates CurrentUser with the average workout duration over the past 7 days.
     *
     */
    public static void getAvgWorkoutDur() throws SQLException {
        CurrentUser.setAvgWorkout(0.0);
        double val = fetchAverage(MetricTypes.WKTDURATION);
        CurrentUser.setAvgWorkout(val);
    }
    /**
     * Updates DailyMetrics of CurrentUser with new DailyMetric information
     *
     * @param dm DailyMetric new daily metric information
     */
    public static void updateDailyMetrics(DailyMetric dm) throws SQLException {
        String sql = """
                INSERT INTO daily_metrics
               (user_id, metric_date, weight, sleep, calories, wktduration)
            VALUES (?, ?, ?,     ?,     ?,        ?)
            ON DUPLICATE KEY UPDATE
              weight      = VALUES(weight),
              sleep       = VALUES(sleep),
              calories    = VALUES(calories),
              wktduration = VALUES(wktduration)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, UserController.getUserId());
            p.setDate(2, Date.valueOf(dm.getDate()));

            if (dm.getW()   != null) p.setDouble(3, dm.getW());
            else p.setNull(3, java.sql.Types.DOUBLE); if (dm.getS()   != null) p.setDouble(4, dm.getS());
            else p.setNull(4, java.sql.Types.DOUBLE);

            if (dm.getC( )   != null) p.setDouble(5, dm.getC());
            else p.setNull(5, java.sql.Types.DOUBLE);

            if (dm.getWkt() != null) p.setDouble(6, dm.getWkt());
            else p.setNull(6, java.sql.Types.DOUBLE);

            p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex.getMessage());
        }
    }
}
