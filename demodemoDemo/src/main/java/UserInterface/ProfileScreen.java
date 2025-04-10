/**
 * this class creates a scene that shows profile info
 */
package UserInterface;

import UserInformation.UserStorage;
import UserInterface.addExercise.AddExerciseDialog;
import UserInterface.addExercise.AddWeightDialog;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends Scenes{

    GridBagConstraints c;
    /**
     * Constructs a ProfileScreen object
     *
     * @param frame which scene is created on
     */
    public ProfileScreen(JFrame frame) {
        createAndShowGUI(frame);
    }
    /**
     * sets the panel layout to GridBagLayout and initializes
     * the GridBagConstraints
     *
     */
    private void panelLayout() {
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    /**
     * creates a ProfileSreen using the super's createAndShowGUI
     * method and adds title with profile info
     * @param
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Profile");

        frame.setLayout(new GridBagLayout());

        panel.add(createNameLabel(), c);
        panel.add(createWeightLabel(), c);

        // TODO: don't want to pass frame to create method
        panel.add(createAddWeightButton(frame), c);
        panel.add(createBackButton(frame), c);
        frame.add(panel);

        // TODO get class list and display
    }

    /**
     * creates and positions name label
     *
     * @return JLabel containing name of user
     */
    private JLabel createNameLabel() {
        JLabel label = new JLabel("Name: " + UserStorage.getName());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        return label;
    }
    /**
     * creates and positions weight label
     *
     * @return JLabel containing weight of user
     */
    private JLabel createWeightLabel() {
        JLabel label = new JLabel("Weight: " + UserStorage.getWeight());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;

        return label;
    }

    /**
     * creates button to display scene to log weight
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton createAddWeightButton(JFrame frame) {
        JButton button = new JButton("Add Weight");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;

        button.addActionListener(e -> {
            new AddWeightDialog(frame);
        });

        return button;
    }

    /**
     * creates button to load and display previous scene
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, UserMenuScene.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;

        return button;
    }

    /**
     * creates a new profileScreen
     *
     * @param frame which scene is created on
     *
     */
    public static void submittedNewScene(JFrame frame) {
        //refreshLogTable();
        new ProfileScreen(frame);
    }

}
