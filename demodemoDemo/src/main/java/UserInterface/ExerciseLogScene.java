package UserInterface;

import FitnessCourse.Exercise;
import UserInformation.UserStorage;
import UserInterface.addExercise.AddExerciseDialog;
import UserInterface.addExercise.LogCSVReaderWriter;
import main.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseLogScene extends Scenes{

    public ExerciseLogScene(JFrame frame){
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        //TODO could we put this somewhere else??
        new LogCSVReaderWriter("src/resources/testCreateExercise.csv");
        LogCSVReaderWriter.readCSV();

        panel.add(addWorkoutButton(frame));
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

    private JButton addWorkoutButton(JFrame frame) {
        JButton workoutButton = new JButton("Add Workout!");
        workoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutButton.setMaximumSize(new Dimension(FRAME_W, 50));

        workoutButton.addActionListener(al -> {
                new AddExerciseDialog(frame);
        });

        return workoutButton;
    }

    private JButton addBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new UserMenuScene(frame);
        });

        return backButton;
    }

    public static void submittedNewScene(JFrame frame) {
        //refreshLogTable();
        new ExerciseLogScene(frame);
    }

    public void refreshLogTable() {}
}
