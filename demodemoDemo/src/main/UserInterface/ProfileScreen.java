package main.UserInterface;

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

        frame.setLayout(new GridLayout());

        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");

        nameLabel.setText(nameLabel.getText() + userStorage.getName());
        panel.add(nameLabel);

        frame.add(panel);

        // get class list and display

    }
}
