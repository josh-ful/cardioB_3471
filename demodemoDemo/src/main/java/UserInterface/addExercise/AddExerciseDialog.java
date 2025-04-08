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
     *
     *
     * @param frame which scene is created on
     */
    public AddExerciseDialog(JFrame frame) {
        createAndShowGUI(frame);
    }
    /**
     *
     *
     * @param frame which dialog is created on
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
     *
     *
     * @return
     */
    private static JLabel getExDescriptionLabel() {
        return new JLabel("Exercise Description: ");
    }
    /**
     *
     *
     * @return
     */
    private static JLabel getExNameLabel() {
        return new JLabel("Exercise Name: ");
    }
    /**
     *
     * @param
     * @return
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
