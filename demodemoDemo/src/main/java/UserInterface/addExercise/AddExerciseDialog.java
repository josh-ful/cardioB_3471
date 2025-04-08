/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts; called from
 * ExerciseLogScene.java
 */

package UserInterface.addExercise;

import UserInterface.ExerciseLogScene;
import Controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExerciseDialog{
    private JFrame newFrame;
    private JTextField nameField;
    private JTextField descriptionField;
    /**
     * Constructs AddExerciseDialog object
     *
     * @param frame which scene is created on
     */
    public AddExerciseDialog(JFrame frame) {
        createAndShowGUI(frame);
    }
    /**
     * creates a AddExerciseDialog with specific size and texts with buttons
     *
     * @param frame which scene is created on
     */
    protected void createAndShowGUI(JFrame frame) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        newFrame = new JFrame("Enter exercise Information");

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));

        nameField = new JTextField();
        descriptionField = new JTextField();

        panel.add(getExNameLabel());
        panel.add(nameField);
        panel.add(getExDescriptionLabel());
        panel.add(descriptionField);
        panel.add(getSubmitButton(frame));

        newFrame.add(panel);
        newFrame.pack();
        newFrame.setVisible(true);
    }
    /**
     * gets description of exercise in label form
     *
     * @return JLabel with description of exercise
     */
    private static JLabel getExDescriptionLabel() {
        return new JLabel("Exercise Description: ");
    }
    /**
     * gets name of exercise in label form
     *
     * @return JLabel with name of exercise
     */
    private static JLabel getExNameLabel() {
        return new JLabel("Exercise Name: ");
    }
    /**
     * gets button that submits information of exercise to list
     * @param frame which scene is created on
     * @return button that submits information to storage
     */
    private JButton getSubmitButton(JFrame frame) {
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController.enterExercise(nameField.getText(), descriptionField.getText());
                newFrame.dispose();
                ExerciseLogScene.submittedNewScene(frame);
            }
        });

        return submit;
    }

}
