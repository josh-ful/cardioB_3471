package UserInterface;

import FitnessCourse.Exercise;
import UserInformation.UserStorage;
import UserInterface.addExercise.AddExerciseDialog;
import UserInterface.addExercise.LogCSVReaderWriter;

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

    //TODO: Probably none of this logic is correct, don't want to have info through the screen
    private void createEL_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);
        new LogCSVReaderWriter("src/resources/testCreateExercise.csv");
        LogCSVReaderWriter.readCSV();

        //JTable logTable = logTable();

        panel.add(addWorkoutButton());
        panel.add(addTextELog());
        panel.add(logTable());

        frame.add(panel);
        System.out.println(UserStorage.getExercises());
    }


    //TODO: make this a table? I think ScrollPane is over the top
    public static JScrollPane logTable(){
        String[] columnNames = {"Name", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(LogCSVReaderWriter.setToMatrix(), columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));

        return scrollPane;
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
        workoutButton.setMaximumSize(new Dimension(FRAME_W, 50));

        workoutButton.addActionListener(al -> {
                new AddExerciseDialog();
        });

        return workoutButton;
    }

    public static void submittedNewScene() {
        //refreshLogTable();
        new ExerciseLogScene(frame);
    }

    public void refreshLogTable() {}
}
