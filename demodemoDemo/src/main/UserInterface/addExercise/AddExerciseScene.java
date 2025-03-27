package main.UserInterface.addExercise;

import main.Controller.Controller;
import main.UserInterface.ExerciseLogScene;
import main.UserInterface.HomeScreen;
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
                frame.setVisible(false);
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
