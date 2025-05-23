package UserInterface;

import FitnessCourse.Course;
import FitnessCourse.Exercise;
import Controller.TrainerController;
import UserInterface.Trainer.TrainerManageCoursesExercisesScene;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
/*
 * this class represents a SearchExercisesDialog object
 * containing information about SearchExercisesDialogs
 */
public class SearchExercisesDialog extends JDialog {
    private final Course course;
    private JTextField searchField;
    private JList<Exercise> resultList;
    private DefaultListModel<Exercise> listModel;
    /**
     * constructs a SearchExercisesDialog object
     *
     * @param parent JFrame which dialog is pertaining to
     * @param course Course to be searched
     */
    public SearchExercisesDialog(JFrame parent, Course course) {
        super(parent, "Search Exercises", true);
        this.course = course;
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    /**
     * initializes components of search
     *
     */
    private void initComponents() {
        JPanel main = new JPanel(new BorderLayout(10,10));

        //search bar at top
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        top.add(searchField);
        JButton goBtn = new JButton("Go");
        goBtn.addActionListener(e -> doSearch());
        top.add(goBtn);
        main.add(top, BorderLayout.NORTH);

        //list of results (Exercises)
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        main.add(new JScrollPane(resultList), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add Selected");
        addBtn.addActionListener(e -> {
            try {
                onAdd();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());
        buttons.add(addBtn);
        buttons.add(cancelBtn);
        main.add(buttons, BorderLayout.SOUTH);

        setContentPane(main);
    }
    /**
     * performs search
     *
     */
    private void doSearch() {
        String term = searchField.getText().trim();
        listModel.clear();
        if (term.isEmpty()) return;
        List<Exercise> results = TrainerController.searchExercises(term);
        results.forEach(listModel::addElement);
    }
    /**
     * state of exercise on add
     *
     */
    private void onAdd() throws SQLException {
        Exercise selected = resultList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select an exercise.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int nextIndex = TrainerController.getCourseExercisesForCourse(course.getId()).size() + 1;
        TrainerController.linkExistingExerciseToCourse(course.getId(), selected.getId(), nextIndex);
        JOptionPane.showMessageDialog(this, "Exercise linked successfully!");
        dispose();
        new TrainerManageCoursesExercisesScene((JFrame)getParent(), course);
    }
}
