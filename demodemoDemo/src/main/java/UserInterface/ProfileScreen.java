/**
 * this class creates a scene that shows profile info
 */
package UserInterface;

import UserInformation.UserStorage;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends Scenes{

    public ProfileScreen(JFrame frame) {
        createAndShowGUI(frame);
    }


    @Override
    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Profile");

        frame.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setText(nameLabel.getText() + UserStorage.getName());

        panel.add(nameLabel);
        frame.add(panel);

        // get class list and display
    }


}
