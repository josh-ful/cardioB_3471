package UserInterface;

import Controller.TrainerController;
import FitnessCourse.Course;
import FitnessCourse.CourseExercise;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

abstract public class ActiveClassScene extends Scenes {
    protected final Course course;
    protected final List<CourseExercise> exercises;
    protected int sessionID;

    protected JLabel totalTimeLabel;
    protected JLabel currentExerciseLabel;
    protected JLabel exerciseTimeLabel;
    protected Timer totalTimer, exerciseTimer;
    protected int totalSecs = 0, exerciseSecs = 0;

    public ActiveClassScene(JFrame frame, Course course) throws SQLException {
        this.course = course;
        //fetch ordered list of exercises
        this.exercises = TrainerController.getCourseExercisesForCourse(course.getId());
        createAndShowGUI(frame);
        startTimers();
    }

    private void startTimers() {
        //total workout timer
        totalTimer = new Timer(1000, e -> {
            totalSecs++;
            totalTimeLabel.setText(formatTime(totalSecs));
        });
        totalTimer.start();

        //per-exercise timer
        exerciseTimer = new Timer(1000, e -> {
            exerciseSecs++;
            exerciseTimeLabel.setText(formatTime(exerciseSecs));
        });
        exerciseTimer.start();
    }

    private String formatTime(int secs) {
        int m = secs / 60, s = secs % 60;
        return String.format("%02d:%02d", m, s);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) throws SQLException {
        super.createAndShowGUI(frame);
        panel.removeAll();
        panel.setLayout(new BorderLayout(10,10));

        //Total workout timer
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
        /*
        list.addListSelectionListener((ListSelectionListener) e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedValue() != null) {
                CourseExercise ce = list.getSelectedValue();
                //currentExerciseLabel.setText(ce.getOrder() + ". " + ce.getExercise().getName());
                exerciseSecs = 0;
                exerciseTimeLabel.setText("00:00");
            }
        });
        */
        panel.add(new JScrollPane(list), BorderLayout.EAST);

        /*
        //rest button and stop hosting at bottom of screen
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton restBtn = new JButton("Rest");
        restBtn.addActionListener(e -> {
            list.clearSelection();
            currentExerciseLabel.setText("Rest");
            exerciseSecs = 0;
            exerciseTimeLabel.setText("00:00");
        });
        south.add(restBtn);

        JButton stopBtn = new JButton("End Class");
        stopBtn.addActionListener(e -> {
            totalTimer.stop();
            exerciseTimer.stop();
            TrainerController.setCourseJoinable(course.getId(), false);
            JOptionPane.showMessageDialog(frame, "Class ended.");
            new TrainerMenuScene(frame);
        });
        south.add(stopBtn);

        panel.add(south, BorderLayout.SOUTH);
        */

        frame.setContentPane(panel);
        frame.revalidate();
    }

}
