/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts; called from
 * ExerciseLogScene.java
 */

package main.UserInterface.addExercise;

import main.UserInterface.Scenes;
import main.Controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExerciseScene extends Scenes{
    public AddExerciseScene() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JDialog dialog = new JDialog();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));

        JLabel nameLabel = new JLabel("Exercise Name: ");
        JTextField nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Exercise Description: ");
        JTextField descriptionField = new JTextField();

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController.enterExercise(nameField.getText(), descriptionField.getText());
                //close dialog based on valid input and button click
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(submit);

        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }
}
