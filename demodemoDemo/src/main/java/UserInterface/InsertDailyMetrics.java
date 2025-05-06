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
    private final JTextField weightField   = new JTextField(6);
    private final JTextField sleepField    = new JTextField(6);
    private final JTextField caloriesField = new JTextField(6);
    private final JTextField workoutField  = new JTextField(6);
    private final JTextField dateField = new JTextField(10);


    public InsertDailyMetrics(Frame owner) {
        super(owner, "Set Your Weekly Goals", true);
        setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel grid = new JPanel(new GridLayout(4,2,10,10));
        grid.add(new JLabel("Weight (lbs):"));
        grid.add(weightField);
        grid.add(new JLabel("Sleep (hrs/day):"));
        grid.add(sleepField);
        grid.add(new JLabel("Calories/day:"));
        grid.add(caloriesField);
        grid.add(new JLabel("Workout (min/day):"));
        grid.add(workoutField);
        grid.add(new JLabel("Date (dd/MM/yyyy):"));
        grid.add(dateField);

        add(grid, BorderLayout.CENTER);
        add(getSaveBtn(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
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
                return;
            }
//            catch (SQLException ex){
//                JOptionPane.showMessageDialog(InsertDailyMetrics.this,
//                    "Could not save goals:\n" + ex.getMessage(),
//                    "Save Failed", JOptionPane.ERROR_MESSAGE);
//            }
        });

        return save;
    }

}
