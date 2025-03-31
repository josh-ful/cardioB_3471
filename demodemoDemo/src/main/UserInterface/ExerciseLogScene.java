package main.UserInterface;

import main.UserInformation.UserStorage;
import main.UserInterface.addExercise.AddExerciseScene;
import main.UserInterface.addExercise.LogCSVReaderWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseLogScene extends Scenes{
    public static JFrame frame;

    public ExerciseLogScene(JFrame frame){
        createEL_SCENE(frame);
        this.frame = frame;
    }

    private void createEL_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);
        //JScrollPane scrollPane = new LogCSVReaderWriter("testCreateExercise.csv").;

        panel.add(addWorkoutButton(frame));
        panel.add(addTextELog());
        //LogCSVReaderWriter.readCSV();
        panel.add(LogCSVReaderWriter.logTable());

        frame.add(panel);
        System.out.println(UserStorage.getExercises());

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

        workoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddExerciseScene();
            }
        });

        return workoutButton;
    }
    public static void submittedNewScene() {
        new ExerciseLogScene(frame);
    }
}
