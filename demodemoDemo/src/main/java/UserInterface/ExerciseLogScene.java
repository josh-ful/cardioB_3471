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
    /**
     * Constructs a ExerciseLogScene object
     *
     * @param frame which scene is created on
     */
    public ExerciseLogScene(JFrame frame){
        createAndShowGUI(frame);
    }
    /**
     * creates a ExerciseLogScene using the super's createAndShowGUI
     * method and adds text and a table
     *
     * @param frame which scene is created on
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        //TODO could we put this somewhere else??
        LogCSVReaderWriter.readCSV();

        panel.add(addWorkoutButton(frame));
        panel.add(addTextELog());
        panel.add(logTable());
        panel.add(createBackButton(frame, UserMenuScene.class));

        frame.add(panel);
        System.out.println(UserStorage.getExercises());
    }
    /**
     * makes a scroll pane with log information
     *
     * @return JScrollPane containing exercise names and descriptions
     */
    //TODO: make this a table? I think ScrollPane is over the top
    public static JScrollPane logTable(){
        LogCSVReaderWriter.setFileName("src/resources/testCreateExercise.csv");
        LogCSVReaderWriter.readCSV();
        String[] columnNames = {"Name", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(LogCSVReaderWriter.setToMatrix(), columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));

        return scrollPane;
    }
    /**
     * adds title
     *
     * @return JLabel with title text
     */
    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Exercise Log!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }
    /**
     * adds button to add a workout
     *
     * @param frame which scene is created on
     * @return JButton that creates AddExerciseDialog scene
     */
    private JButton addWorkoutButton(JFrame frame) {
        JButton workoutButton = new JButton("Add Workout!");
        workoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutButton.setMaximumSize(new Dimension(FRAME_W, 50));

        workoutButton.addActionListener(al -> new AddExerciseDialog(frame));

        return workoutButton;
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
    /**
     * creates new ExerciseLogScene
     *
     * @param frame which scene is created on
     */

    public static void submittedNewScene(JFrame frame) {
        //refreshLogTable();
        new ExerciseLogScene(frame);
    }
    /**
     *
     *
     */
    public void refreshLogTable() {}
}
