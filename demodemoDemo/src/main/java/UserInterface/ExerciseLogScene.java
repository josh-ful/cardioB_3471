package UserInterface;

import UserInformation.CurrentUser;
import UserInterface.addExercise.AddExerciseDialog;
import UserInterface.addExercise.ExerciseLogHelper;
import Controller.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * this class contains functionality to read and to write from a
 * CSV file and use the information to include in our classes
 */
public class ExerciseLogScene extends Scenes{

    private static JTable table;
    ExerciseLogHelper exerciseLogHelper;
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
        // src/resources/testCreateExercise.csv

        panel.add(createWorkoutButton(frame));
        panel.add(createExerciseLogText());
        panel.add(createLogTable());
        panel.add(createBackButton(frame));

        frame.add(panel);
        System.out.println(CurrentUser.getExercises());
    }
    /**
     * makes a scroll pane with log information
     *
     * @return JScrollPane containing exercise names and descriptions
     */
    //TODO: make this a table? I think ScrollPane is over the top
    public static JScrollPane createLogTable(){
        String[] columnNames = {"Name", "Description"};

        DefaultTableModel tableModel = new DefaultTableModel(UserController.getTableMatrix(), columnNames);
        table = new JTable(tableModel);
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
    private JLabel createExerciseLogText() {
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
    private JButton createWorkoutButton(JFrame frame) {
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
    private JButton createBackButton(JFrame frame) {
        return createBackButton(frame, UserMenuScene.class);
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
    public static void updateTable() {
        String[] columnNames = {"Name", "Description"};
        DefaultTableModel model = (DefaultTableModel) new DefaultTableModel(UserController.getTableMatrix(), columnNames);
        table.setModel(model);
        table.repaint();
    }
}
