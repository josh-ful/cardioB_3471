// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: UserMenuScene.java is an extension
// of Scenes.java that creates a scene with a menu and a
// welcome method


package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * this class is an extension of Scenes.java that creates a
 * scene with a menu and a welcome method
 */
public class UserMenuScene extends Scenes{
    JPanel panel = new JPanel();
    GridBagConstraints constraints = new GridBagConstraints();
    /**
     * Constructs a new 'UserMenuScene' with the specified frame
     *
     * @param frame
     */
    public UserMenuScene(JFrame frame){
        createUM_SCENE(frame);
    }
    /**
     * sets layout of panel to GridBagLayout
     */
    private void panelLayout() {
        panel.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }
    /**
     *
     *
     * @param frame
     */
    public void createUM_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        addTextMenu();
        initMenu(frame);

        frame.add(panel);
    }
    /**
     * adds title and text to panel
     */
    public void addTextMenu() {
        JLabel welcomeText = new JLabel("Welcome!");
        welcomeText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        welcomeText.setForeground(Color.BLACK);
        constraints.gridx = 0;  // Column
        constraints.gridy = 1;  // Row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.CENTER; // Center it
        constraints.fill = GridBagConstraints.NONE;
        panel.add(welcomeText, constraints);

        JLabel promptText = new JLabel("Ready to get your CardioB in today?");
        promptText.setForeground(Color.BLACK);
        constraints.gridx = 0;  // Column
        constraints.gridy = 2;  // Row
        constraints.anchor = GridBagConstraints.CENTER; // Center it
        panel.add(promptText, constraints);
    }
    /**
     *
     *
     * @param frame
     */
    private void initMenu(JFrame frame) {
        JMenuBar menu = getjMenu(frame);
        constraints.gridx = 0;  // Column
        constraints.gridy = 0;  // Row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(menu, constraints);
    }
    /**
     * creates menu with menu items to lead to other scenes
     *
     * @return menu bar with menu items
     */
    static JMenuBar getjMenu( JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();

        ImageIcon icon = new ImageIcon("main/resources/menuIcon.png");
        Image image = icon.getImage();
        int newWidth = 50; // Desired width
        int newHeight = 50; // Desired height
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        menu.setIcon(icon);

        JMenuItem profileItem = new JMenuItem("Profile");
        profileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProfileScreen(frame);
            }
        });
        menu.add(profileItem);

        JMenuItem classItem = new JMenuItem("Classes");
        classItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClassListScene(frame);
            }
        });
        menu.add(classItem);

        JMenuItem workoutLogItem = new JMenuItem("Personal Workout Log");
        workoutLogItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExerciseLogScene(frame);
            }
        });
        menuBar.add(menu);
        menu.add(workoutLogItem);
        menu.addSeparator();
        return menuBar;
    }
}