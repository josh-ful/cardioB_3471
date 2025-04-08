// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: UserMenuScene.java is an extension
// of Scenes.java that creates a scene with a menu and a
// welcome method
/*
 * this class is an extension of Scenes.java that creates a
 * scene with a menu and a welcome method
 */

package UserInterface;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//TODO fix some method/formatting things here
public class UserMenuScene extends Scenes{
    GridBagConstraints constraints = new GridBagConstraints();
    /**
     *
     * Constructs a UserMenuScene object
     *
     * @param frame which scene is created on
     */
    public UserMenuScene(JFrame frame){
        createUM_SCENE(frame);
    }
    /**
     *
     * sets the panel layout to GridBagLayout
     *
     */
    private void panelLayout() {
        panel.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }
    /**
     * creates a UserMenuScene using the super's createAndShowGUI
     * method and adds on a menu and text
     *
     */
    protected void createUM_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();


        addTextMenu();
        initMenu(frame);

        frame.add(panel);
    }
    /**
     * adds a welcome message using constraints
     *
     */
    private void addTextMenu() {
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
     * adds a menu to panel using getjMenu method
     *
     * @param frame which menu is added to
     */
    private void initMenu(JFrame frame) {
        JMenuBar menu = getjMenu(frame);
        constraints.gridx = 0;  // Column
        constraints.gridy = 0;  // Row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST; // Center it
        constraints.fill = GridBagConstraints.NONE;
        panel.add(menu, constraints);
    }
    /**
     * creates menu bar which has menu items
     * which create new scenes
     *
     * @param frame which scenes are created on
     * @return JMenuBar which holds menu items
     */
    private static JMenuBar getjMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();

        menu.setIcon(getMenuIcon());

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
        menu.add(workoutLogItem);

        menuBar.add(menu);
        menu.addSeparator();

        return menuBar;
    }
    /**
     * creates image icon out of the resource
     *
     * @return ImageIcon that is scaled
     */
    private static ImageIcon getMenuIcon() {
        ImageIcon icon = new ImageIcon("src/resources/menuIcon.png");
        Image image = icon.getImage();
        int newWidth = 50; // Desired width
        int newHeight = 50; // Desired height
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        return icon;
    }
}