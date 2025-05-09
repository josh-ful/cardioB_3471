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
    private JTextField durationField;
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
        durationField = new JTextField();

        panel.add(getExNameLabel());
        panel.add(nameField);
        panel.add(getExDescriptionLabel());
        panel.add(descriptionField);
        panel.add(getButtons());


        newFrame.add(panel);
        newFrame.pack();
        newFrame.setVisible(true);
    }

    private JPanel getButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(getSubmitButton());
        buttonPanel.add(getCancelButton());
        return buttonPanel;
    }

    private JButton getCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e-> {
            newFrame.dispose();
        });
        return cancelButton;
    }

    /**
     * gets description of exercise in label form
     *
     * @return JLabel with description of exercise
     */
    private static JLabel getExDescriptionLabel() {
        return new JLabel("Description: ");
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
     * @return button that submits information to storage
     */
    private JButton getSubmitButton() {
        JButton submit = new JButton("Submit");
        submit.addActionListener(ae -> {
            try {
                if (!nameField.getText().equals("")) {
                    UserController.addExercise(nameField.getText(), descriptionField.getText());
                    newFrame.dispose();
                    ExerciseLogScene.updateTable();
                }
                else {
                    throw new Exception("Please enter a valid exercise name");
                }

            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(newFrame,
                        "Make sure the exercise details are correct",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }

        });

        return submit;
    }

}
