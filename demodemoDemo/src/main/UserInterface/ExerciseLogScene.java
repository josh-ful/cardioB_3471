/**
 * this class does something at some point I think
 */

package main.UserInterface;

import main.UserInterface.addExercise.AddExerciseScene;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
<<<<<<< Updated upstream
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseLogScene extends Scenes{
    JButton addWorkoutButton = new JButton("Add Workout!");
    JPanel panel = new JPanel();

    JTable j;

    String[] columnNames = {"Exercise Name",
            "Exercise Description"};
    Object[][] data = {
            {"Squats", "sadofiasdj"},
            {"Bench Press", "Most fun"},
            {"Deadlift", "RIP lower back"}
    };



=======
/**
 * this class is an extension of Scenes.java that creates a
 * scene with list of exercises and ability to log
 */
public class ExerciseLogScene extends Scenes{
    /**
     * Constructs a new 'ExerciseLogScene' with the specified frame
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public ExerciseLogScene(JFrame frame){
        createEL_SCENE(frame);
        final Class<?>[] columnClass = new Class[] {
                String.class, String.class, String.class, Integer.class, Boolean.class
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };

        j = new JTable(model);
        j.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(j);
        panel.add(j);

        addWorkoutButton(frame);
        addWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddExerciseScene a = new AddExerciseScene();
            }
        });
    }
<<<<<<< Updated upstream

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createEL_SCENE(JFrame frame) {
        panelLayout();
=======
    /**
     *
     *
     * @param frame
     */
    private void createEL_SCENE(JFrame frame) {
>>>>>>> Stashed changes
        super.createAndShowGUI(frame);

        addTextELog();

        frame.add(panel);
    }
<<<<<<< Updated upstream

    public void addTextELog() {
=======
    /**
     *
     *
     * @return JLabel
     */
    private JLabel addTextELog() {
>>>>>>> Stashed changes
        JLabel exerciseText = new JLabel("Exercise Log!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(exerciseText);
    }
<<<<<<< Updated upstream

    public void addWorkoutButton(JFrame frame) {
        addWorkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addWorkoutButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(addWorkoutButton);
=======
    /**
     *
     *
     * @return add workout button
     */
    private JButton addWorkoutButton(JFrame frame) {
        JButton workoutButton = new JButton("Add Workout!");
        workoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

>>>>>>> Stashed changes
        /*profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });*/
    }
    public void addExerciseList(JFrame frame) {

    }
}
