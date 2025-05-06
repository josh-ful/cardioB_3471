package UserInterface;

import Controller.UserController;
import UserInformation.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class InsertDailyMetrics extends JDialog {
    GridBagConstraints c;

    private final JTextField weightField;
    private final JTextField sleepField;
    private final JTextField caloriesField;
    private final JTextField workoutField;
    private final JTextField dateField;

    public InsertDailyMetrics(Frame owner) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Add daily metrics");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        JLabel instructionsLbl = new JLabel("Add your daily metrics here. " +
                "Any field left blank will not be inserted");

        weightField   = new JTextField(6);
        sleepField    = new JTextField(6);
        caloriesField = new JTextField(6);
        workoutField  = new JTextField(6);
        dateField = new JTextField(10);

        addRow(panel, instructionsLbl, 0, 0);
        addRow(panel, new JLabel("Weight (lbs):"), 1, 0);
        addRow(panel, weightField, 1, 1);
        addRow(panel, new JLabel("Sleep (hrs/day):"), 2, 0);
        addRow(panel, sleepField, 2, 1);
        addRow(panel, new JLabel("Calories/day:"), 3, 0);
        addRow(panel, caloriesField, 3, 1);
        addRow(panel, new JLabel("Workout (min/day):"), 4, 0);
        addRow(panel, workoutField, 4, 1);
        addRow(panel, new JLabel("Date (dd/MM/yyyy):"), 5, 0);
        addRow(panel, dateField, 5, 1);

        add(panel);
        add(getSaveBtn(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private JButton getSaveBtn(){
        JButton save = new JButton("Save");
        save.addActionListener(e ->{
            double s, w, c, wkt;

            try {
                w = Double.parseDouble(weightField.getText());
                s = Double.parseDouble(sleepField.getText());
                c = Double.parseDouble(caloriesField.getText());
                wkt = Double.parseDouble(workoutField.getText());

                UserController.addDailyMetric(w, s, c, wkt);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers…");
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(InsertDailyMetrics.this,
                    "Could not save goals:\n" + ex.getMessage(),
                    "Save Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        return save;
    }

    public void addRow(JPanel panel, Component comp, int row, int col) {
        c = new GridBagConstraints();
        c.gridx = col;
        c.gridy = row;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        if(col == 1){
            c.weightx = 1.0;
        }
        else{
            c.weightx = 0.0;
        }
        panel.add(comp, c);
    }
}
