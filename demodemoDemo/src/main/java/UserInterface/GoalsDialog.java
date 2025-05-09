package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import Controller.UserController;
import UserInformation.CurrentUser;
/*
 * this class represents a GoalsDialog object
 * containing information about GoalsDialogs
 */
public class GoalsDialog extends JDialog {
    private final JTextField weightField   = new JTextField(6);
    private final JTextField sleepField    = new JTextField(6);
    private final JTextField caloriesField = new JTextField(6);
    private final JTextField workoutField  = new JTextField(6);
    /**
     * constructs a GoalsDialog object
     *
     * @param owner Frame which dialog is displayed on
     */
    public GoalsDialog(Frame owner) {
        super(owner, "Set Your Weekly Goals", true);
        setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // preload current goals
        weightField.setText(String.valueOf(CurrentUser.getWeightGoal()));
        sleepField.setText(String.valueOf(CurrentUser.getAvgSleepGoal()));
        caloriesField.setText(String.valueOf(CurrentUser.getAvgCaloriesGoal()));
        workoutField.setText(String.valueOf(CurrentUser.getAvgWorkoutGoal()));

        JPanel grid = new JPanel(new GridLayout(4,2,10,10));
        grid.add(new JLabel("Weight (lbs):"));
        grid.add(weightField);
        grid.add(new JLabel("Sleep (hrs/day):"));
        grid.add(sleepField);
        grid.add(new JLabel("Calories/day:"));
        grid.add(caloriesField);
        grid.add(new JLabel("Workout (min/day):"));
        grid.add(workoutField);

        add(grid, BorderLayout.CENTER);

        JButton save = new JButton("Save");
        save.addActionListener(this::onSave);
        add(save, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }
    /**
     * saves inputted goals
     *
     * @param e ActionEvent to perform
     */
    private void onSave(ActionEvent e) {
        double w, s, c, wkt;
        try {
            w   = Double.parseDouble(weightField.getText());
            s   = Double.parseDouble(sleepField.getText());
            c   = Double.parseDouble(caloriesField.getText());
            wkt = Double.parseDouble(workoutField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers…");
            return;
        }
        // do the database work in the background
        new SwingWorker<Void, Void>() {
            @Override protected Void doInBackground() throws Exception {
                // parse once on the EDT
                double w   = Double.parseDouble(weightField.getText());
                double s   = Double.parseDouble(sleepField.getText());
                double c   = Double.parseDouble(caloriesField.getText());
                double wkt = Double.parseDouble(workoutField.getText());

                // go to the DB
                UserController.updateUserGoals(w, s, c, wkt);
                return null;
            }

            @Override protected void done() {
                try {
                    get();  // rethrow any errors
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(GoalsDialog.this,
                            "Could not save goals:\n" + ex.getMessage(),
                            "Save Failed", JOptionPane.ERROR_MESSAGE);
                }
                GoalsDialog.this.dispose();
            }
        }.execute();

    }
}
