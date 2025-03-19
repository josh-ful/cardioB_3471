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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;



//From main we are starting program so we need to bring the UI to scene 1 (login),
// and then from there try to give control to the individual controllers based on userType

//Data Abstraction (Locally based implementation--remove when implementing database)
//Map of valid usernames and passwords [DONE]
//Login scene: [DONE]
//Right now in the main file
//Could be extracted into its own class later

//within the scene the interactions should confirm validity of login [DONE]
//then assign the user with a session
//and in creating a session assign the correct type of controller
//in order to bring it to the next screen
//imo what makes sense is have a session obj that we have 3 different constructors for
//if we have a session constructed with a admin type, in its creation its should bring
//the user to the next scene? in its construction create a new gui? that overrides the login gui'


//TO DO:
//Create Login GUI, that leads to two different screens login & register [DONE]
//Create fields in the login scene that check with the local map to see if the login info is valid [DONE]
//Create fields in the register scene, that accept valid formatted info and add that data to the map [DONE]
//Extract login and register button scenes to separate classes & link to one gui


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
        Map<String,String> logins  = new LinkedHashMap<>();
        logins.put("Noah", "Mathew");
        logins.put("Josh", "Fulton");
        logins.put("Kiera", "Shepperd");
        logins.put("Emily", "Wokoek");
        logins.put("Carter", "Lewis");
        logins.put("Lawson", "Hale");

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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame frame = new JFrame("Login");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Login ™");
                JPanel panel = new JPanel();
                panel.setBackground(Color.RED); // Set background color

                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                JTextField username = new JTextField(20);
                JPasswordField password = new JPasswordField(20);

                JButton loginButton = new JButton("Login");
                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String user = username.getText();
                        String pass = new String(password.getPassword());
                        if(logins.containsKey(user) && pass.equals(logins.get(user))){
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Login Successful!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                        else{
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Login Failed!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                    }
                });
                panel.add(username);
                panel.add(password);
                panel.add(loginButton);
                frame.add(panel);
                frame.setVisible(true);

                //repaint();

            }
        });

        //loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(100, 50));
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        //loginButton.setHorizontalAlignment(SwingConstants.TOP);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Register");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Register ™");
                JPanel panel = new JPanel();
                panel.setBackground(Color.RED); // Set background color


                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                JTextField username = new JTextField(20);
                JPasswordField password = new JPasswordField(20);

                JButton registerButton = new JButton("Register");
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String user = username.getText();
                        String pass = new String(password.getPassword());
                        if (!logins.containsKey(user)) {
                            logins.put(user, pass);
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Registration Successful!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        } else {
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Registration Failed!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                    }
                });
                panel.add(username);
                panel.add(password);
                panel.add(registerButton);
                frame.add(panel);
                frame.setVisible(true);
            }
        });

        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(100, 50));
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        //registerButton.setSize(20,20);

        ImageIcon icon = new ImageIcon("src/main/resources/cardioB_logo.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(400, 425, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(icon);


        c.anchor = GridBagConstraints.PAGE_END;




        //frame.add(iconLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 90;
        frame.add(iconLabel, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 30;
        frame.add(companyName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 30;
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
        //System.out.println("mommy nodes"); >:( no
    }
}