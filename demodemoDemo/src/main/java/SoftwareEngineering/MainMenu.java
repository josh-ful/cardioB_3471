/*
Author: Trello Fellows
Date Created: 3/18/2025
File Name: MainMenu.java
Description: Holds the main file and holds the display of the menu (for now)

Date Last Modified: 3/18/2025


PUT YOUR NAME HERE:
Josh Fulton

 */



package SoftwareEngineering;


import javax.swing.*;
import java.awt.*;



public class MainMenu {

    public class companyDetail extends JPanel {
        public companyDetail() {
            JLabel companyName = new JLabel("CardioB™");
            companyName.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
            companyName.setForeground(Color.BLACK);
            //companyName.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(companyName);
        }
    }
    public class buttons extends JPanel {
        public buttons() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JButton loginButton = new JButton("Login");
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginButton.setMaximumSize(new Dimension(200, 50));
            loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

            JButton registerButton = new JButton("Register");
            registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            registerButton.setMaximumSize(new Dimension(200, 50));
            registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

            this.add(loginButton);
            this.add(Box.createVerticalStrut(25));
            this.add(registerButton);
        }
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame();
        //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
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

        JButton loginButton = new JButton("Login");
        //loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(100, 50));
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        //loginButton.setHorizontalAlignment(SwingConstants.TOP);

        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(100, 50));
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        //registerButton.setSize(20,20);

        ImageIcon icon = new ImageIcon("src/main/resources/cardioB_logo.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(icon);


        c.anchor = GridBagConstraints.PAGE_END;




        //frame.add(iconLabel, c);

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

    public static void main(String[] args) {
        new MainMenu().createAndShowGUI();
        System.out.println("mommy nodes");
    }
}