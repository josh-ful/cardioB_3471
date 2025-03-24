package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userMenuScene extends Scenes{
    JButton profileButton = new JButton("Profile");
    JButton logButton = new JButton("Log Exercise");
    JButton classButton = new JButton("Classes");
    JPanel panel = new JPanel();

    public userMenuScene(JFrame frame){
        createUM_SCENE(frame);
        addProfileButton(frame);
        addLogButton(frame);
        addClassButton(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


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
        welcomeText.setAlignmentY(Component.TOP_ALIGNMENT);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setVerticalAlignment(SwingConstants.TOP);
        JLabel promptText = new JLabel("Ready to get your CardioB in today?");
        promptText.setForeground(Color.BLACK);
        promptText.setAlignmentY(Component.TOP_ALIGNMENT);
        promptText.setHorizontalAlignment(SwingConstants.CENTER);
        promptText.setVerticalAlignment(SwingConstants.TOP);
        panel.add(welcomeText);
        panel.add(promptText);
    }

    public void addProfileButton(JFrame frame) {
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(profileButton);
        /*profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });*/
    }
    public void addLogButton(JFrame frame) {
        logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(logButton);
        /*profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });*/
    }
    public void addClassButton(JFrame frame) {
        classButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        classButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(classButton);
        /*profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });*/
    }


}