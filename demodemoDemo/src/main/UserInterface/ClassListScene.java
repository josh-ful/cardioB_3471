/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassListScene extends Scenes{
    JButton addWorkoutButton = new JButton("Add Workout!");
    JPanel panel = new JPanel();
    JTextArea textArea = new JTextArea(10, 30);
    JScrollPane scrollPane = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public ClassListScene(JFrame frame){
        createCL_SCENE(frame);
        addScrollClassList(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createCL_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        addTextELog();

        frame.add(panel);
    }

    public void addTextELog() {
        JLabel exerciseText = new JLabel("Classes!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(exerciseText);
    }

    public void addScrollClassList(JFrame frame) {
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(scrollPane);
    }
}

