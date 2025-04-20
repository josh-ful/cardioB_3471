package UserInterface.addExercise;

import Controller.UserController;
import UserInterface.ExerciseLogScene;
import UserInterface.ProfileScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWeightDialog extends JDialog {
    private JFrame newFrame;
    private JTextField weightField;
    /**
     * Constructs AddExerciseDialog object
     *
     * @param frame which scene is created on
     */
    public AddWeightDialog(JFrame frame) {
        createAndShowGUI(frame);
    }

    private JPanel panelLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));
        return panel;
    }

    /**
     * creates a AddExerciseDialog with specific size and texts with buttons
     *
     * @param frame which scene is created on
     */


    protected void createAndShowGUI(JFrame frame) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        newFrame = new JFrame("Enter weight:");

        JPanel panel = panelLayout();

        weightField = new JTextField();
        panel.add(new JTextField());
        panel.add(getSubmitButton(frame));

        newFrame.add(panel);
        newFrame.pack();
        newFrame.setVisible(true);
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
                // TODO throw exceptions about invalid input (ie not a integer is entered)
                UserController.enterWeight(Integer.parseInt(weightField.getText()));
                newFrame.dispose();
                ProfileScreen.submittedNewScene(frame);
            }
        });

        return submit;
    }
}
