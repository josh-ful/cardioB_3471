/**
 * this class creates a scene that shows profile info
 */
package main.UserInterface;

import main.UserInformation.UserStorage;

import javax.swing.*;
import java.awt.*;
/**
 * this class is an extension of Scenes.java that creates a
 * scene that displays profile information
 */
public class ProfileScreen extends Scenes{
    /**
     * Constructs a new 'ProfileScreen' with the specified frame
     *
     * @param frame
     */
    public ProfileScreen(JFrame frame) {
        createAndShowGUI(frame);
    }

    /**
     * displays profile information to panel
     *
     * @param frame
     */
    @Override
    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Profile");

        frame.setLayout(new GridLayout());

        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");

        nameLabel.setText(nameLabel.getText() + UserStorage.getName());
        panel.add(nameLabel);

        frame.add(panel);

        // get class list and display
    }
}
