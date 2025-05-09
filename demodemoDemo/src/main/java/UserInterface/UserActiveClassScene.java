package UserInterface;

import Controller.TrainerController;
import Controller.UserController;
import FitnessCourse.Course;
import FitnessCourse.CourseExercise;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
/*
 * this class represents a UserActiveClassScene object
 * containing information about courses
 */
public class UserActiveClassScene extends ActiveClassScene {
    private Timer pollTimer;
    /**
     * constructs a UserActiveClassScene object
     *
     * @param frame JFrame
     * @param course Course
     */
    public UserActiveClassScene(JFrame frame, Course course) throws SQLException {

        super(frame, course);
        //cange the DB flag so users can join
        sessionID = UserController.getSessionId(course);
        UserController.setUserAsJoined(sessionID);
        // start polling  every second
        pollTimer = new Timer(1000, e -> refreshCurrentExercise(course.getId()));
        pollTimer.setInitialDelay(0);
        pollTimer.start();

    }
    /**
     * refreshes current exercise if any changes occur
     *
     * @param courseId int
     */
    private void refreshCurrentExercise(int courseId) {
        String latest = UserController.getCurrentExerciseName(courseId);
        // if it changed on the server, update the label and reset the exercise timer
        if (!latest.equals(currentExerciseLabel.getText())) {
            currentExerciseLabel.setText(latest);
            exerciseSecs = 0;
            exerciseTimeLabel.setText("00:00");
        }
    }
    /**
     * leaving class by stopping timers
     *
     * @param frame JFrame
     */
    private void leaveClass(JFrame frame) throws SQLException {
        pollTimer.stop();
        totalTimer.stop();
        exerciseTimer.stop();
        new ClassListScene(frame);
    }
    /**
     * creates and displays gui
     *
     * @param frame JFrame
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
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
        panel.add(new JScrollPane(list), BorderLayout.EAST);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));


        JButton stopBtn = new JButton("Leave Class");
        stopBtn.addActionListener(e -> {
            try {
                leaveClass(frame);
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
