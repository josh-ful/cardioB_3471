package main.UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.UserInterface.*;

public class LR_Scenes extends Scenes {
    public JTextField username;
    public JPasswordField password;

    private void panelLayout() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    public void createLR_SCENE(JFrame frame) {
        username = new JTextField(20);
        password = new JPasswordField(20);
        panel = new JPanel();

        panelLayout();
        super.createAndShowGUI(frame);

        panel.add(getUsernameLabel());
        panel.add(username);
        panel.add(getPasswordLabel());
        panel.add(password);

        frame.add(panel);
    }

    private JLabel getUsernameLabel(){
        return new JLabel("Username: ");
    }

    private JLabel getPasswordLabel(){
        return new JLabel("Password: ");
    }

    public JButton getBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen(frame);
            }
        });

        return backButton;
    }







}
