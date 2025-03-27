package main.UserInterface;

import main.UserInterface.addExercise.AddExerciseScene;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.UserInterface.addExercise.AddExerciseScene;

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
    public void addExerciseList(JFrame frame) {

    }
}
