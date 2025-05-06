package UserInterface;

import Controller.UserController;
import UserInformation.CurrentUser;
import UserInformation.DailyMetrics.DailyMetric;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Dialog for inserting daily metrics with a date picker.
 */
public class InsertDailyMetrics extends JDialog {
    private final JTextField weightField;
    private final JTextField sleepField;
    private final JTextField caloriesField;
    private final JTextField workoutField;
    private final JSpinner dateSpinner;

    public InsertDailyMetrics(Frame owner) {
        super(owner, "Add Daily Metrics", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.WEST;

        JLabel instructions = new JLabel(
                "Add your daily metrics here. Any field left blank will be skipped."
        );
        addRow(panel, instructions, c, 0, 0);

        weightField   = new JTextField(6);
        sleepField    = new JTextField(6);
        caloriesField = new JTextField(6);
        workoutField  = new JTextField(6);

        // Date picker using JSpinner
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);

        addRow(panel, new JLabel("Weight (lbs):"),   c, 1, 0);
        addRow(panel, weightField,                   c, 1, 1);
        addRow(panel, new JLabel("Sleep (hrs/day):"), c, 2, 0);
        addRow(panel, sleepField,                    c, 2, 1);
        addRow(panel, new JLabel("Calories/day:"),  c, 3, 0);
        addRow(panel, caloriesField,                 c, 3, 1);
        addRow(panel, new JLabel("Workout (min/day):"), c, 4, 0);
        addRow(panel, workoutField,                  c, 4, 1);
        addRow(panel, new JLabel("Date:"),          c, 5, 0);
        addRow(panel, dateSpinner,                   c, 5, 1);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(getSaveBtn(owner), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private JButton getSaveBtn(Frame owner) {
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            try {
                Double w    = parseDoubleOrNull(weightField.getText());
                Double s    = parseDoubleOrNull(sleepField.getText());
                Double cal  = parseDoubleOrNull(caloriesField.getText());
                Double wkt  = parseDoubleOrNull(workoutField.getText());

                // Convert spinner value (java.util.Date) to LocalDate
                Date utilDate = (Date) dateSpinner.getValue();
                LocalDate date = utilDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // Call controller method accepting LocalDate
                UserController.addDailyMetric(date, w, s, cal, wkt);

                JOptionPane.showMessageDialog(owner,
                        "Successfully saved daily metrics!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(owner,
                        "Please enter valid numbers in metric fields.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(owner,
                        "Could not save daily metrics:\n" + ex.getMessage(),
                        "Save Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        return save;
    }

    private Double parseDoubleOrNull(String text) {
        if (text == null || text.isBlank()) return null;
        return Double.valueOf(text.trim());
    }

    private void addRow(JPanel panel, Component comp,
                        GridBagConstraints c, int row, int col) {
        c.gridx = col;
        c.gridy = row;
        if (col == 1) {
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
        } else {
            c.weightx = 0.0;
            c.fill = GridBagConstraints.NONE;
        }
        panel.add(comp, c);
    }
}