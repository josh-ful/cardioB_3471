package UserInterface;

import Controller.TrainerController;

import javax.swing.*;
import java.awt.*;

public class TrainerCreateClassDialog extends JDialog {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField scheduleField;

    public TrainerCreateClassDialog(JFrame parent) {
        super(parent, "Create New Class", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Class Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        add(new JLabel("Schedule (e.g. Mon 10-11) ONLY FILL FOR GROUP CLASSES!:"));
        scheduleField = new JTextField();
        add(scheduleField);

        JButton createBtn = new JButton("Create");
        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            String time = scheduleField.getText().trim();
            TrainerController.createClass(name, description,time);
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
