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

public class AddExerciseDialog{
    private JFrame newFrame;

    public AddExerciseDialog(JFrame frame) {
        createAndShowGUI(frame);
    }

    protected void createAndShowGUI(JFrame frame) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        newFrame = new JFrame("Enter exercise Information");

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
                newFrame.dispose();
                ExerciseLogScene.submittedNewScene(frame);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(submit);

        newFrame.add(panel);
        newFrame.pack();
        newFrame.setVisible(true);
    }

}
