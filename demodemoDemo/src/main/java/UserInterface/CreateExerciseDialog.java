package UserInterface;

import FitnessCourse.Course;
import Controller.TrainerController;
import FitnessCourse.CourseExercise;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CreateExerciseDialog extends JDialog {
    private final Course course;
    private JTextField nameField;
    private JTextArea descArea;

    public CreateExerciseDialog(JFrame parent, Course course) {
        super(parent, "Create New Exercise", true);
        this.course = course;
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void initComponents() {
        JPanel main = new JPanel(new BorderLayout(10,10));

        //input panel
        JPanel fields = new JPanel(new GridLayout(4,1,5,5));
        fields.add(new JLabel("Exercise Name:"));
        nameField = new JTextField(20);
        fields.add(nameField);
        fields.add(new JLabel("Description:"));
        descArea = new JTextArea(5, 20);
        fields.add(new JScrollPane(descArea));
        main.add(fields, BorderLayout.CENTER);

        //buttons panel
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> onSave());
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());
        buttons.add(saveBtn);
        buttons.add(cancelBtn);
        main.add(buttons, BorderLayout.SOUTH);

        setContentPane(main);
    }

    private void onSave() {
        String name = nameField.getText().trim();
        String desc = descArea.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int nextIndex = TrainerController.getCourseExercisesForCourse(course.getId()).size() + 1;
            TrainerController.addExerciseToCourse(course.getId(), name, desc, nextIndex);
            JOptionPane.showMessageDialog(this, "Exercise added successfully!");
            dispose();
            new ManageCoursesExercisesScene((JFrame)getParent(), course);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error adding exercise: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}