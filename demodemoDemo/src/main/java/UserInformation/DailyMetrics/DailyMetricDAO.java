package UserInformation.DailyMetrics;

import Controller.UserController;
import UserInterface.graphs.Point;
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

    public static List<Point> fetchMetrics(MetricTypes type) throws SQLException {
        String sql = "SELECT " + type +" , metric_date FROM daily_metrics WHERE user_id=? ORDER BY metric_date";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, UserController.getUserId());
            ResultSet r = p.executeQuery();
            List<Point> out = new ArrayList<>();
            while (r.next()) {
                Point dm = new Point();
                dm.setX(r.getDate("date"));
                dm.setY(r.getDouble(type.toString()));

                out.add(dm);
            }
            return out;
        }
    }
}
