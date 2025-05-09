package UserInterface.Trainer;

import Controller.TrainerController;
import FitnessCourse.Course;

import javax.swing.*;
import java.awt.*;

public class TrainerEditClassDialog extends JDialog {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField scheduleField;

    /**
     * constructs a TrainerEditClassDialog object
     *
     * @param parent JFrame
     * @param course Course
     */
    public TrainerEditClassDialog(JFrame parent, Course course) {
        super(parent, "Edit Class", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Class Name:"));
        nameField = new JTextField();
        nameField.setText(course.getName());
        add(nameField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        descriptionField.setText(course.getDescription());
        add(descriptionField);

        add(new JLabel("Schedule (e.g. Mon 10-11) ONLY FILL FOR GROUP CLASSES!:"));
        scheduleField = new JTextField();
        scheduleField.setText(course.getTime());
        add(scheduleField);

        JButton createBtn = new JButton("Confirm edits");
        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            String time = scheduleField.getText().trim();
            System.out.println(course.getId() + " " +  name + " " + description + " " + time);
            TrainerController.updateClass(course.getId(), name,description,time);
            dispose();
        });
        add(createBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());
        add(cancelBtn);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
