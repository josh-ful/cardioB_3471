package UserInterface.Trainer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import FitnessCourse.Course;
import Controller.TrainerController;
import UserInterface.Scenes;


public class TrainerReportsScene extends Scenes {

    /**
     * constructs a TrainerReportsScene object
     *
     * @param frame JFrame
     */
    public TrainerReportsScene(JFrame frame) {
        createAndShowGUI(frame);
    }


    /**
     * creates and displays GUI for trainer report scene
     *
     * @param frame JFrame
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        //clear existing components
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));

        //title
        JLabel title = new JLabel("View Reports", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        //fetch courses for current trainer
        List<Course> courses = TrainerController.getClassesForCurrentTrainer();

        //list panel for courses
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (Course course : courses) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            JLabel nameLabel = new JLabel(course.getName());
            JButton viewBtn = new JButton("View Report");
            viewBtn.addActionListener(e -> {
                new TrainerReportDetailsScene(frame, course);
            });
            row.add(nameLabel);
            row.add(viewBtn);
            listPanel.add(row);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Back button to return to trainer menu
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            try {
                new TrainerMenuScene(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(backBtn, BorderLayout.SOUTH);

        // Set content pane and refresh
        frame.setContentPane(panel);
        frame.revalidate();
    }
}
