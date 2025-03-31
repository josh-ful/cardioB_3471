package main.UserInterface;

import main.FitnessCourse.Exercise;
import main.UserInformation.UserStorage;
import main.UserInterface.addExercise.AddExerciseDialog;
import main.UserInterface.addExercise.LogCSVReaderWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseLogScene extends Scenes{
    public static JFrame frame;

    public ExerciseLogScene(JFrame frame){
        createEL_SCENE(frame);
        ExerciseLogScene.frame = frame;
    }

    private void createEL_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);
        new LogCSVReaderWriter("src/resources/testCreateExercise.csv");
        LogCSVReaderWriter.readCSV();

        panel.add(addWorkoutButton(frame));
        panel.add(addTextELog());
        panel.add(logTable());

        frame.add(panel);
        System.out.println(UserStorage.getExercises());


    }



    public static JScrollPane logTable(){
        String[] columnNames = {"Name", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(setToMatrix(), columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));

        return scrollPane;
    }

    public static String[][] setToMatrix(){
        int i= 0;
        String [][] matrix = new String[UserStorage.getExercises().size()][2];
        for(Exercise e : UserStorage.getExercises()){
            matrix[i][0] = e.getName();
            //System.out.println(matrix[i][0]);
            matrix[i][1] = e.getDescription();
            // System.out.println(matrix[i][1]);
            i++;
        }
        return matrix;
    }

    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Exercise Log!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }

    private JButton addWorkoutButton() {
        JButton workoutButton = new JButton("Add Workout!");
        workoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        workoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddExerciseDialog();
            }
        });

        return workoutButton;
    }
    public static void submittedNewScene() {
        new ExerciseLogScene(frame);
    }
}
