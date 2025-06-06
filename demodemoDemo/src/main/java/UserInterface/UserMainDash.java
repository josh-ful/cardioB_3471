package UserInterface;

import Controller.UserController;
import UserInformation.CurrentUser;
import UserInformation.DailyMetrics.*;
import UserInterface.graphs.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
/*
 * this class represents a UserMainDash object
 * containing information about UserMainDash
 */
public class UserMainDash extends Scenes {
    GridBagConstraints constraints = new GridBagConstraints();
    /**
     * constructs a UserMainDash object
     *
     * @param frame JFrame
     */
    public UserMainDash(JFrame frame) throws SQLException {
        createUM_SCENE(frame);
    }
    /**
     * sets the panel layout to GridBagLayout and initializes
     * the GridBagConstraints
     *
     */
    protected void panelLayout() {
        panel.setLayout(new BorderLayout(10,10));
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }

    // Updated to convert sql Date properly via LocalDate
    private ChartPanel makeTimeSeriesChart(MetricTypes type, String title) throws SQLException {
        List<Point> data = DailyMetricDAO.fetchAllMetrics(type);
        TimeSeries series = new TimeSeries(type.getName());
        for (Point dm : data) {
            // dm.getX() returns java.sql.Date
            java.sql.Date sqlDate = (java.sql.Date) dm.getX();
            LocalDate ld = sqlDate.toLocalDate();
            // Day constructor: day, month, year
            Day day = new Day(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear());
            series.add(day, dm.getY());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                "Date",
                type.name(),
                dataset,
                false,
                true,
                false
        );

        return new ChartPanel(chart);
    }
    /**
     * creates a createUM_SCENE displaying dashboard information
     *
     * @param frame JFrame
     */
    protected void createUM_SCENE(JFrame frame) throws SQLException {
        new UserController();
        super.createAndShowGUI(frame);
        panelLayout();

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcome = new JLabel("Hello, " + CurrentUser.getName() + "!");
        welcome.setFont(new Font("Roboto", Font.BOLD, 24));
        top.add(welcome);
        panel.add(top, BorderLayout.NORTH);

        JPanel chartPanel = new JPanel(new BorderLayout(10,10));
        JPanel chartsGrid = new JPanel(new GridLayout(2,2,5,5));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.WEIGHT, "Weight"));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.SLEEP, "Sleep (hrs)"));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.CALORIES, "Calories"));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.WKTDURATION, "Workout Duration"));
        chartPanel.add(chartsGrid, BorderLayout.CENTER);

        panel.add(chartPanel, BorderLayout.CENTER);

        JPanel navBar = new JPanel(new GridLayout(1,4));
        JButton dashBtn = new JButton("Daily Metrics");
        dashBtn.addActionListener(e -> {
            try {
                new UserDailyMetricsGraphs(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton classBtn = new JButton("Classes");
        classBtn.addActionListener(e -> {
            try {
                new ClassListScene(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton exerciseLogBtn = new JButton("Exercise Log");
        exerciseLogBtn.addActionListener(e -> new ExerciseLogScene(frame));
        JButton profileBtn = new JButton("Profile");
        profileBtn.addActionListener(e -> {
            try {
                new Profile(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        navBar.add(dashBtn);
        navBar.add(classBtn);
        navBar.add(exerciseLogBtn);
        navBar.add(profileBtn);
        panel.add(navBar, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }
}