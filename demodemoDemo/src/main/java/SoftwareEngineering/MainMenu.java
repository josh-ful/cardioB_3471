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


    public class buttons extends JPanel {
        public buttons() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JButton loginButton = new JButton("Login");
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton registerButton = new JButton("Register");
            registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createVerticalStrut(200));
            this.add(loginButton);
            this.add(registerButton);
        }
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CardioB ™");


        frame.setSize(800, 600);
        frame.add(new buttons());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu().createAndShowGUI();
        System.out.println("mommy nodes");
    }
}