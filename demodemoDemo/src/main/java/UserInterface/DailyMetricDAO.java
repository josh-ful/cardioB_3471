package UserInterface;

import main.DBConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailyMetricDAO {
    private final DataSource ds;
    public DailyMetricDAO(DataSource ds) { this.ds = ds; }

    public static List<DailyMetric> fetchMetrics(int userId, MetricTypes type) throws SQLException {
        String sql = "SELECT metric_value, date FROM daily_metric WHERE user_id=? AND metric_type=? ORDER BY date";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, userId);
            p.setString(2, type.name());
            ResultSet r = p.executeQuery();
            List<DailyMetric> out = new ArrayList<>();
            while (r.next()) {
                DailyMetric dm = new DailyMetric();
                dm.setUserId(userId);
                dm.setType(type);
                dm.setValue(r.getDouble("metric_value"));
                dm.setDate(r.getDate("date").toLocalDate());
                out.add(dm);
            }
            return out;
        }
    }
}
