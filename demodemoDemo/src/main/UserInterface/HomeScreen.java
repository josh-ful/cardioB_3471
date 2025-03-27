// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: HomeScreen.java is an extension
// of Scenes.java and creates a scene with a title and
// options to register or log in

package main.UserInterface;

import main.UserInterface.Login.LoginScene;
import main.UserInterface.Login.RegisterScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends Scenes{
    public HomeScreen(JFrame frame) {
        createAndShowGUI(frame);
    }

    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Trello Fellows®");

        frame.setSize(450, 800);

        JLabel companyName = new JLabel("CardioB");
        companyName.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
        companyName.setForeground(Color.BLACK);
        companyName.setAlignmentY(Component.CENTER_ALIGNMENT);
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        companyName.setVerticalAlignment(SwingConstants.TOP);

        JButton loginButton = getLoginButton(frame);
        loginButton.setMaximumSize(new Dimension(100, 50));
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        JButton registerButton = getRegisterButton(frame);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(100, 50));
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        ImageIcon icon = new ImageIcon("src/main/resources/cardioB_logo.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(300, 400, Image.SCALE_SMOOTH);

        icon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(icon);

        c.anchor = GridBagConstraints.PAGE_END;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 100;
        frame.add(iconLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        frame.add(companyName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        frame.add(loginButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 15;
        frame.add(registerButton, c);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static JButton getLoginButton(JFrame frame) {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScene(frame);
            }
        });
        return loginButton;
    }

    private static JButton getRegisterButton(JFrame frame) {
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScene(frame);
            }
        });
        return registerButton;
    }
}
