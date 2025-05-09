package UserInterface.Trainer;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import FitnessCourse.Course;
import Controller.TrainerController;
import UserInterface.Scenes;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class TrainerReportDetailsScene extends Scenes {

    /**
     * constructs a TrainerReportDetailsScene
     *
     * @param frame JFrame
     * @param course Course
     */
    public TrainerReportDetailsScene(JFrame frame, Course course) {
        createAndShowGUI(frame, course);
    }


    /**
     * creates and displays GUI for trainer detailed report scene
     *
     * @param frame JFrame
     * @param course Course
     */
    private void createAndShowGUI(JFrame frame, Course course) {
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("Report: " + course.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);

        // Content panel
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Registrations over time (self-paced and group courses)
        Map<String, Integer> regData = TrainerController.getRegistrationCounts(course.getId());
        DefaultCategoryDataset regDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : regData.entrySet()) {
            regDataset.addValue(entry.getValue(), "Registrations", entry.getKey());
        }
        JFreeChart regChart = ChartFactory.createLineChart(
                "Registrations Over Time",
                "Date",
                "Registrations",
                regDataset
        );
        ChartPanel regChartPanel = new ChartPanel(regChart);
        regChartPanel.setPreferredSize(new Dimension(600, 300));
        content.add(regChartPanel);

        // Additional graph for group courses: session join counts
        if ("group".equalsIgnoreCase(course.getType())) {
            Map<String, Integer> sessionData = TrainerController.getSessionJoinCounts(course.getId());
            DefaultCategoryDataset sessionDataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Integer> entry : sessionData.entrySet()) {
                sessionDataset.addValue(entry.getValue(), "Joined", entry.getKey());
            }
            JFreeChart sessionChart = ChartFactory.createLineChart(
                    "Session Join Counts",
                    "Session Time",
                    "People Joined",
                    sessionDataset
            );
            ChartPanel sessionChartPanel = new ChartPanel(sessionChart);
            sessionChartPanel.setPreferredSize(new Dimension(600, 300));
            content.add(Box.createVerticalStrut(20)); // spacing
            content.add(sessionChartPanel);
        }

        // Scrollable content
        JScrollPane scroll = new JScrollPane(content);
        panel.add(scroll, BorderLayout.CENTER);

        // Back button
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> new TrainerReportsScene(frame));
        panel.add(backBtn, BorderLayout.SOUTH);

        // Finalize
        frame.setContentPane(panel);
        frame.revalidate();
    }
}
