// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: UserMenuScene.java is an extension
// of Scenes.java that creates a scene with a menu and a
// welcome method
/*
 * this class is an extension of Scenes.java that creates a
 * scene with a menu and a welcome method
 */

package UserInterface;

import Controller.UserController;
import UserInformation.CurrentUser;
import UserInformation.DailyMetrics.DailyMetricDAO;
import UserInformation.DailyMetrics.MetricTypes;
import UserInterface.graphs.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import UserInformation.DailyMetrics.DailyMetric;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

//TODO fix some method/formatting things here
public class UserMainDash extends Scenes{
    GridBagConstraints constraints = new GridBagConstraints();
    /**
     *
     * Constructs a UserMenuScene object
     *
     * @param frame which scene is created on
     */
    public UserMainDash(JFrame frame) throws SQLException {
        createUM_SCENE(frame);
    }
    /**
     *
     * sets the panel layout to GridBagLayout
     *
     */
    protected void panelLayout() {
        panel.setLayout(new BorderLayout(10,10));
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }

    private ChartPanel makeTimeSeriesChart(MetricTypes type, String title) throws SQLException {
        List<Point> data = DailyMetricDAO.fetchMetrics(type);
        TimeSeries series = new TimeSeries(type.name());
        for (Point dm : data) {
            series.add(new Day(java.util.Date.from(dm.getX().toInstant())), dm.getY());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                "Date",//x-axis
                type.name(),//y-axis
                dataset,//data
                false,
                true,
                false
        );

        return new ChartPanel(chart);
    }


    /**
     * creates a UserMenuScene using the super's createAndShowGUI
     * method and adds on a menu and text
     *
     */
    protected void createUM_SCENE(JFrame frame) throws SQLException {
        new UserController();
        super.createAndShowGUI(frame);
        panelLayout();

        //welcome text
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcome = new JLabel("Hello, " + CurrentUser.getName() + "!");
        welcome.setFont(new Font("Roboto", Font.BOLD, 24));
        top.add(welcome);
        panel.add(top, BorderLayout.NORTH);

        //addTextMenu();
        //initMenu(frame);
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout(10,10));
        JPanel chartsGrid = new JPanel(new GridLayout(2,2,5,5));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.WEIGHT,   "Weight"));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.SLEEP,    "Sleep (hrs)"));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.CALORIES, "Calories"));
        chartsGrid.add(makeTimeSeriesChart(MetricTypes.WKTDURATION,  "Workout Duration"));
        chartPanel.add(chartsGrid, BorderLayout.CENTER);

        panel.add(chartPanel, BorderLayout.CENTER);
        //nav bar with three buttons on the bottom
        JPanel navBar = new JPanel();
        navBar.setLayout(new GridLayout(1, 4));

        JButton dashBtn = new JButton("Daily Metrics");
        dashBtn.addActionListener(e -> {
                    System.out.println("Clicking daily metrics tab");
                    new UserDailyMetricsGraphs(frame);
                }
        );

        JButton classBtn = new JButton("Classes");
        classBtn.addActionListener(e -> {
                    System.out.println("Clicking  classes tab");
                    new ClassListScene(frame);
                }
        );

        JButton exerciseLogBtn = new JButton("Exercise Log");
        exerciseLogBtn.addActionListener(e -> {
                    System.out.println("Clicking profile tab");
                    new ExerciseLogScene(frame);
                }
        );

        JButton profileBtn = new JButton("Profile");
        profileBtn.addActionListener(e -> {
                    System.out.println("Clicking profile tab");
                    new Profile(frame);
                }
        );



        navBar.add(dashBtn);
        navBar.add(classBtn);
        navBar.add(exerciseLogBtn);
        navBar.add(profileBtn);
        panel.add(navBar, BorderLayout.SOUTH);

        frame.add(panel);

        frame.setContentPane(panel);
        frame.revalidate();
    }
    /**
     * adds a welcome message using constraints
     *
     */
    private void addTextMenu() {
        JLabel welcomeText = new JLabel("Welcome!");
        welcomeText.setFont(new Font("Roboto", Font.BOLD, 60));
        welcomeText.setForeground(Color.BLACK);
        constraints.gridx = 0;  // Column
        constraints.gridy = 1;  // Row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.CENTER; // Center it
        constraints.fill = GridBagConstraints.NONE;
        panel.add(welcomeText, constraints);

        JLabel promptText = new JLabel("Ready to get your CardioB in today?");
        promptText.setForeground(Color.BLACK);
        constraints.gridx = 0;  // Column
        constraints.gridy = 2;  // Row
        constraints.anchor = GridBagConstraints.CENTER; // Center it
        panel.add(promptText, constraints);
    }
    /**
     * adds a menu to panel using getjMenu method
     *
     * @param frame which menu is added to
     */
    private void initMenu(JFrame frame) {
        JMenuBar menu = getjMenu(frame);
        constraints.gridx = 0;  // Column
        constraints.gridy = 0;  // Row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST; // Center it
        constraints.fill = GridBagConstraints.NONE;
        panel.add(menu, constraints);
    }
    /**
     * creates menu bar which has menu items
     * which create new scenes
     *
     * @param frame which scenes are created on
     * @return JMenuBar which holds menu items
     */
    private static JMenuBar getjMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();

        menu.setIcon(getMenuIcon());

        JMenuItem profileItem = new JMenuItem("Dashboard");
        profileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserDailyMetricsGraphs(frame);
            }
        });
        menu.add(profileItem);

        JMenuItem classItem = new JMenuItem("Classes");
        classItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClassListScene(frame);
            }
        });
        menu.add(classItem);

        JMenuItem workoutLogItem = new JMenuItem("Personal Workout Log");
        workoutLogItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExerciseLogScene(frame);
            }
        });
        menu.add(workoutLogItem);

        menuBar.add(menu);
        menu.addSeparator();

        return menuBar;
    }
    /**
     * creates image icon out of the resource
     *
     * @return ImageIcon that is scaled
     */
    private static ImageIcon getMenuIcon() {
        ImageIcon icon = new ImageIcon("src/resources/menuIcon.png");
        Image image = icon.getImage();
        int newWidth = 50; // Desired width
        int newHeight = 50; // Desired height
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        return icon;
    }
}