package UserInterface;

import FitnessCourse.Course;
import FitnessCourse.CourseExercise;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
/*
 * this class represents a UserSelfPacedClassScene object
 * containing information about UserSelfPacedClassScene
 */
public class UserSelfPacedClassScene extends ActiveClassScene {
    /**
     * constructs a UserSelfPacedClassScene object
     *
     * @param frame JFrame
     * @param course Course
     */
    public UserSelfPacedClassScene(JFrame frame, Course course) {
        super(frame, course);
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
            }
        });
        panel.add(new JScrollPane(list), BorderLayout.EAST);

        //rest button and stop hosting at bottom of screen
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton restBtn = new JButton("Rest");
        restBtn.addActionListener(e -> {
            list.clearSelection();
            currentExerciseLabel.setText("Resting");
            exerciseSecs = 0;
            exerciseTimeLabel.setText("00:00");
        });
        south.add(restBtn);

        JButton stopBtn = new JButton("End Session");
        stopBtn.addActionListener(e -> {
            totalTimer.stop();
            exerciseTimer.stop();
            JOptionPane.showMessageDialog(frame, "Class ended.");
            try {
                new ClassListScene(frame);
            } catch (SQLException ex) {
                ex.printStackTrace();
                //throw new RuntimeException(ex);
            }
        });
        south.add(stopBtn);

        panel.add(south, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }

}
