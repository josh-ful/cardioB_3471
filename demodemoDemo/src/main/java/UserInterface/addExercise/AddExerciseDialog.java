/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts; called from
 * ExerciseLogScene.java
 */

package UserInterface.addExercise;

import UserInterface.ExerciseLogScene;
import UserInterface.Scenes;
import Controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExerciseDialog extends Scenes{
    public AddExerciseDialog() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Enter exercise Information");
        super.createAndShowGUI(frame);
        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

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
                frame.dispose();
                ExerciseLogScene.submittedNewScene();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(submit);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
