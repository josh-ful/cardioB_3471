/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package UserInterface;

import UserInterface.Login.LR_Dialog;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class ClassListScene extends Scenes{
    JTextArea textArea = new JTextArea(10, 30);
    /**
     * Constructs a ClassListScene object
     *
     * @param frame which scene is created on
     */
    public ClassListScene(JFrame frame){
        createAndShowGUI(frame);
    }
    /**
     * creates a ClassListScene using the super's createAndShowGUI
     * method and adds on a menu and text
     *
     * @param frame which scene is created on
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        panel.add(addTextELog());
        panel.add(addScrollClassList());
        panel.add(addWorkoutButton());
        panel.add(addBackButton(frame));

        frame.add(panel);
    }
    /**
     * adds text to scene
     *
     * @return JLabel with title and font
     */
    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Classes!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }
    /**
     * creates scroll pane for classes
     *
     * @return JScrollPane for classes
     */
    private JScrollPane addScrollClassList() {
        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(FRAME_DIM);

        return scrollPane;
    }
    /**
     * create a button leading to add workout dialog page
     *
     * @return JButton to create add workout dialog scene
     */
    private JButton addWorkoutButton() {
        JButton button = new JButton("Register for New Class");
        button.addActionListener(e -> {
            new LR_Dialog(true);
        });
       return button;
    }
    /**
     * adds button leading to previous scene
     *
     * @param frame which scene is created on
     * @return button with back button functionality
     */
    private JButton addBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new UserMenuScene(frame);
        });

        return backButton;
    }

}

