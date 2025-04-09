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

        JLabel nameLabel = new JLabel("Name: " + UserStorage.getName() + "\n");
        JLabel weightLabel = new JLabel("Weight: " + UserStorage.getWeight());

        panel.add(new JLabel("Name: " + UserStorage.getName()));
        // TODO: add a seperator here!
        panel.add(new JLabel("Weight: " + UserStorage.getWeight()));

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
     * creates button to load and display previous scene
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, UserMenuScene.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;

//        button.addActionListener(e -> {
//            //ScreenController.goto(UserMenuScene);
//            new UserMenuScene(frame);
//        });

        return button;
    }

    private JButton createAddWeightButton(JFrame frame) {
        JButton button = new JButton("Add Weight");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;

        button.addActionListener(e -> {
            new AddWeightDialog(frame);
        });

        return button;
    }

    public static void submittedNewScene(JFrame frame) {
        //refreshLogTable();
        new ProfileScreen(frame);
    }

}
