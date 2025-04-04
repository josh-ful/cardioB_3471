/**
 * this class creates a scene that shows profile info
 */
package UserInterface;

import UserInformation.UserStorage;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends Scenes{

    GridBagConstraints c;

    public ProfileScreen(JFrame frame) {
        createAndShowGUI(frame);
    }

    private void panelLayout() {
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Profile");

        frame.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setText(nameLabel.getText() + UserStorage.getName());

        panel.add(nameLabel);
        // TODO: don't want to pass frame to create method
        panel.add(createBackButton(frame), c);
        frame.add(panel);

        // TODO get class list and display
    }

    private JLabel createNameLabel() {
        JLabel label = new JLabel("Name: " + UserStorage.getName());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        return label;
    }


    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, UserMenuScene.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;

//        button.addActionListener(e -> {
//            //ScreenController.goto(UserMenuScene);
//            new UserMenuScene(frame);
//        });

        return button;
    }


}
