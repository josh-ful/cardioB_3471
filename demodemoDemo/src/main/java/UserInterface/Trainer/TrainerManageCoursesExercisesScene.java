package UserInterface.Trainer;

import Controller.TrainerController;
import FitnessCourse.Course;
import FitnessCourse.CourseExercise;
import UserInterface.Scenes;
import UserInterface.SearchExercisesDialog;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TrainerManageCoursesExercisesScene extends Scenes {
    private final Course course;
    private JPanel listContainer;
    private JScrollPane scrollPane;


    /**
     * constructs a TrainerManageCoursesExercisesScene
     *
     * @param frame JFrame
     * @param course Course
     */
    public TrainerManageCoursesExercisesScene(JFrame frame, Course course) throws SQLException {
        this.course = course;
        createAndShowGUI(frame);
    }

    /**
     * creates and displays GUI for course manager scene
     *
     * @param frame JFrame
     */
    @Override
    protected void createAndShowGUI(JFrame frame) throws SQLException {
        super.createAndShowGUI(frame);
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));

        //buttons for func
        // TODO (move later?)
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton searchBtn = new JButton("Search for More Exercises");
        searchBtn.addActionListener(e -> {
            new SearchExercisesDialog(frame, course);
        });
        JButton createBtn = new JButton("Create New Exercise");
        createBtn.addActionListener(e -> {
            new TrainerCreateExerciseDialog(frame, course);
        });
        topBar.add(searchBtn);
        topBar.add(createBtn);
        panel.add(topBar, BorderLayout.NORTH);

        //list of current exercises
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(listContainer);
        panel.add(scrollPane, BorderLayout.CENTER);

        //TODO replace with Lawsons back button?
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            // go back to the trainer view
            try {
                new TrainerViewClassesScene(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomBar.add(backBtn);
        panel.add(bottomBar, BorderLayout.SOUTH);

        //load and show
        reloadExercises();

        frame.setContentPane(panel);
        frame.revalidate();
    }

    /**
     * repaints the exercise list after an update to the list
     *
     */
    private void reloadExercises() {
        listContainer.removeAll();

        //pull from DB
        List<CourseExercise> exercises =
                TrainerController.getCourseExercisesForCourse(course.getId());

        for (CourseExercise ce : exercises) {
            JPanel row = new JPanel(new BorderLayout(5,5));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            row.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.GRAY));

            JLabel lbl = new JLabel("<html><b>"+ce.getExercise().getName()+"</b><br/>"
                    +ce.getExercise().getDescription()+"</html>");
            row.add(lbl, BorderLayout.CENTER);

            JButton remove = new JButton("Remove");
            remove.addActionListener(e -> {
                TrainerController.removeCourseExerciseByLinkId(
                        ce.getLinkId(), course.getId()
                );
                reloadExercises();
            });
            row.add(remove, BorderLayout.EAST);

            listContainer.add(row);
        }

        listContainer.revalidate();
        listContainer.repaint();
    }
}
