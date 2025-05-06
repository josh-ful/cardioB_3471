package UserInterface.Trainer;

import Controller.TrainerController;
import FitnessCourse.Course;
import FitnessCourse.CourseExercise;
import UserInterface.ActiveClassScene;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;

public class TrainerActiveClassScene extends ActiveClassScene {
    public TrainerActiveClassScene(JFrame frame, Course course) throws SQLException {
        super(frame, course);
        //cange the DB flag so users can join
        TrainerController.setCourseJoinable(course.getId(), true);
        sessionID = TrainerController.startCourseSession(course.getId(),course.getName());

    }

    @Override
    protected void createAndShowGUI(JFrame frame) throws SQLException {
        super.createAndShowGUI(frame);
        panel.removeAll();
        panel.setLayout(new BorderLayout(10,10));

        //top total workout duration
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Total Time: "));
        totalTimeLabel = new JLabel("00:00");
        top.add(totalTimeLabel);
        panel.add(top, BorderLayout.NORTH);

        //curr exercise and timer
        JPanel center = new JPanel(new BorderLayout(5,5));
        currentExerciseLabel = new JLabel("Rest", SwingConstants.CENTER);
        currentExerciseLabel.setFont(currentExerciseLabel.getFont().deriveFont(24f));
        center.add(currentExerciseLabel, BorderLayout.CENTER);

        exerciseTimeLabel = new JLabel("00:00", SwingConstants.CENTER);
        center.add(exerciseTimeLabel, BorderLayout.SOUTH);
        panel.add(center, BorderLayout.CENTER);

        //list of courses exercises
        DefaultListModel<CourseExercise> model = new DefaultListModel<>();
        for (CourseExercise ce : exercises) model.addElement(ce);
        JList<CourseExercise> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //show only the name
        list.addListSelectionListener((ListSelectionListener) e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedValue() != null) {
                CourseExercise ce = list.getSelectedValue();
                currentExerciseLabel.setText(
                        ce.getOrder() + ". " + ce.getExercise().getName()
                );
                exerciseSecs = 0;
                exerciseTimeLabel.setText("00:00");
                TrainerController.updateCourseSession(sessionID,currentExerciseLabel.getText());
            }
        });
        panel.add(new JScrollPane(list), BorderLayout.EAST);

        //rest button and stop hosting at bottom of screen
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton restBtn = new JButton("Rest");
        restBtn.addActionListener(e -> {
            list.clearSelection();
            currentExerciseLabel.setText("Resting");
            TrainerController.updateCourseSession(sessionID,currentExerciseLabel.getText());
            exerciseSecs = 0;
            exerciseTimeLabel.setText("00:00");
        });
        south.add(restBtn);

        JButton stopBtn = new JButton("End Class");
        stopBtn.addActionListener(e -> {
            totalTimer.stop();
            exerciseTimer.stop();
            TrainerController.setCourseJoinable(course.getId(), false);
            TrainerController.endCourseSession(sessionID);
            JOptionPane.showMessageDialog(frame, "Class ended.");
            try {
                new TrainerMenuScene(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        south.add(stopBtn);

        panel.add(south, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }

}
