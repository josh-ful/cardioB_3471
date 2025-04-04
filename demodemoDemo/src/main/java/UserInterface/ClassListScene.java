/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package UserInterface;

import main.Main;

import javax.swing.*;
import java.awt.*;

public class ClassListScene extends Scenes{
    JTextArea textArea = new JTextArea(10, 30);

    public ClassListScene(JFrame frame){
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        panel.add(addTextELog());
        panel.add(addScrollClassList());
        panel.add(addWorkoutButton());
        panel.add(addBackButton(frame));

        frame.add(panel);
    }

    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Classes!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }

    private JScrollPane addScrollClassList() {
        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(FRAME_DIM);

        return scrollPane;
    }

    private JButton addWorkoutButton() {
        JButton button = new JButton("Add Class");
        button.addActionListener(e -> {
            new LR_Dialog(true);
        });
       return button;
    }

    private JButton addBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new UserMenuScene(frame);
        });

        return backButton;
    }

}

