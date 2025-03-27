package main.UserInterface;

import javax.swing.*;
import java.awt.*;

public class ClassListScene extends Scenes{
    JTextArea textArea = new JTextArea(10, 30);

    public ClassListScene(JFrame frame){
        createCL_SCENE(frame);
    }

    public void createCL_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(addTextELog());
        panel.add(addScrollClassList());
        panel.add(addWorkoutButton());

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
        scrollPane.setMaximumSize(Scenes.FRAME_DIM);

        return scrollPane;
    }

    private JButton addWorkoutButton() {
       return new JButton("Add Workout!");
    }

}

