package main.UserInterface;

import javax.swing.*;
import java.awt.*;

public class ExerciseLogScene extends Scenes{

    public ExerciseLogScene(JFrame frame){
        createEL_SCENE(frame);
    }

    private void createEL_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(addWorkoutButton(frame));
        panel.add(addTextELog());
        frame.add(panel);
    }

    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Exercise Log!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }

    private JButton addWorkoutButton(JFrame frame) {
        JButton workoutButton = new JButton("Add Workout!");
        workoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        /*profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });*/

        return workoutButton;
    }
}
