package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseLogScene extends Scenes{
    JButton addWorkoutButton = new JButton("Add Workout!");
    JPanel panel = new JPanel();

    public ExerciseLogScene(JFrame frame){
        createEL_SCENE(frame);
        addWorkoutButton(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createEL_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        addTextELog();

        frame.add(panel);
    }

    public void addTextELog() {
        JLabel exerciseText = new JLabel("Exercise Log!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(exerciseText);
    }

    public void addWorkoutButton(JFrame frame) {
        addWorkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addWorkoutButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(addWorkoutButton);
        /*profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });*/
    }
}
