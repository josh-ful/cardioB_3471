package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userMenuScene extends Scenes{
    JButton profileButton = new JButton("Profile");
    JButton menuHamburgerButton = new JButton();

    JButton logButton = new JButton("Log Exercise");
    JButton classButton = new JButton("Classes");
    JPanel panel = new JPanel();

    public userMenuScene(JFrame frame){
        addCardiBButton(frame);
        createUM_SCENE(frame);
        addProfileButton(frame);
        addLogButton(frame);
        addClassButton(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); }


    public void createUM_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        addTextMenu();

        frame.add(panel);
    }

    public void addTextMenu() {
        JLabel welcomeText = new JLabel("Welcome!");
        welcomeText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        welcomeText.setForeground(Color.BLACK);
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        //welcomeText.setAlignmentY(Component.TOP_ALIGNMENT);
        //welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        //welcomeText.setVerticalAlignment(SwingConstants.TOP);
        JLabel promptText = new JLabel("Ready to get your CardioB in today?");
        promptText.setForeground(Color.BLACK);
        promptText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(welcomeText);
        panel.add(promptText);
    }

    public void addProfileButton(JFrame frame) {
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(profileButton);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileInfoScene(frame);
            }
        });
    }
    public void addLogButton(JFrame frame) {
        logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(logButton);
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExerciseLogScene(frame);
            }
        });
    }
    public void addClassButton(JFrame frame) {
        classButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        classButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(classButton);
        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassListScene(frame);
            }
        });
    }
    public void addCardiBButton(JFrame frame) {

        menuHamburgerButton.setLocation(10, 10);
        ImageIcon icon = new ImageIcon("src/main/resources/menuIcon.png");
        Image image = icon.getImage();
        int newWidth = 50; // Desired width
        int newHeight = 50; // Desired height
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        icon = new ImageIcon(scaledImage);


        menuHamburgerButton = new JButton(icon);

        menuHamburgerButton.setBorderPainted(false);
        menuHamburgerButton.setFocusPainted(false);
        menuHamburgerButton.setContentAreaFilled(false);



        menuHamburgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Image button clicked!");
            }
        });

        menuHamburgerButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(menuHamburgerButton);
    }


}