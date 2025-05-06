package UserInterface;

import javax.swing.*;

import Controller.Controller;
import UserInformation.CurrentUser;
import UserInterface.Login.OnboardingDialog;

import java.awt.*;
import java.sql.SQLException;

public class Profile extends Scenes{
    private static JPanel metricsPanel;
    GridBagConstraints c;

    private static JLabel currAge;
    private static JLabel currGender;
    private static JLabel currEmail;

    public Profile(JFrame frame) throws SQLException {
        createAndShowGUI(frame);
    }

    @Override
    protected void panelLayout() {
        //north center south layout
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    @Override
    protected void createAndShowGUI(JFrame frame) throws SQLException {
        super.createAndShowGUI(frame);
        //frame.setLocationRelativeTo(frame);
        panelLayout();

        //user info
        panel.add(makeUserInfoPanel(), BorderLayout.NORTH);

        //current metrics compared to goals
        metricsPanel = makeMetricsGoalsPanel();
        panel.add(metricsPanel, BorderLayout.CENTER);

        //buttons
        panel.add(makeButtonBar1(frame), BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.pack();
        frame.setSize(FRAME_DIM);
        frame.setLocationRelativeTo(frame);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel makeUserInfoPanel() {
        JPanel info = new JPanel(new GridLayout(0, 2, 10, 10));
        info.setBorder(
            BorderFactory.createTitledBorder("Profile Information")
        );

        info.add(new JLabel("Username:"));
        info.add(new JLabel(CurrentUser.getName()));

        info.add(new JLabel("User Type:"));
        info.add(new JLabel(CurrentUser.getType()));

        info.add(new JLabel("Age:"));
        currAge = new JLabel(CurrentUser.getAge().toString());
        info.add(currAge);

        info.add(new JLabel("Gender:"));
        currGender = new JLabel(CurrentUser.getGender());
        info.add(currGender);

        info.add(new JLabel("Email:"));
        currEmail = new JLabel(CurrentUser.getEmail());
        info.add(currEmail);

        info.add(getEditOnboardingBtn());

        return info;
    }

    public static void refreshInfoPanel(){
        currAge.setText(CurrentUser.getAge().toString());
        currGender.setText(CurrentUser.getGender());
        currEmail.setText(CurrentUser.getEmail());
    }

    private static JPanel makeMetricsGoalsPanel() throws SQLException {
        // assume MetricService provides these four current numbers
        double currentWeight     = CurrentUser.getCurrentWeight();
        double avgSleep          = CurrentUser.getAvgSleep();
        double avgCalories       = CurrentUser.getAvgCalories();
        double avgWorkoutDur     = CurrentUser.getAvgWorkout();

        // assume GoalService provides saved goals
        double goalWeight        = CurrentUser.getWeightGoal();
        double goalSleep         = CurrentUser.getAvgSleepGoal();
        double goalCalories      = CurrentUser.getAvgCaloriesGoal();
        double goalWorkoutDur    = CurrentUser.getAvgWorkoutGoal();

        JPanel box = new JPanel(new BorderLayout());
        box.setBorder(
            BorderFactory.createTitledBorder("This Week: Current vs. Goal")
        );

        String[] cols = { "", "Current", "Goal" };
        String[][] data = {
                { "Weight",      String.valueOf(currentWeight),   String.valueOf(goalWeight) },
                { "Sleep (hrs)", String.format("%.1f", avgSleep),  String.valueOf(goalSleep) },
                { "Calories",    String.format("%.0f", avgCalories),String.valueOf(goalCalories)},
                { "Workout (min)",String.format("%.0f", avgWorkoutDur),String.valueOf(goalWorkoutDur) }
        };

        JTable table = new JTable(data, cols);
        table.setEnabled(false);
        box.add(new JScrollPane(table), BorderLayout.CENTER);

        return box;
    }

    private JPanel makeButtonBar1(JFrame frame) {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttons.add(getGoalsBtn(frame));
        buttons.add(getResetPasswordBtn());
        buttons.add(getLogoutBtn(frame));
        buttons.add(createBackButton(frame, UserMainDash.class));

        return buttons;
    }

    private JButton getGoalsBtn(JFrame frame) {
        JButton setGoals = new JButton("Set Goals");

        setGoals.addActionListener(e -> {
            GoalsDialog dlg = new GoalsDialog(frame);
            dlg.setVisible(true);
            panel.remove(metricsPanel);
            try {
                metricsPanel = makeMetricsGoalsPanel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            panel.add(metricsPanel, BorderLayout.CENTER);

            panel.revalidate();
            panel.repaint();
        });
        return setGoals;
    }
    private static JButton getEditOnboardingBtn() {
        JButton btnEditOnboarding = new JButton("Edit Information");

        btnEditOnboarding.addActionListener(e->{
            new OnboardingDialog(true);
        });

        return btnEditOnboarding;
    }

    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");

        btnResetPassword.addActionListener(e->{
            new UserResetPasswordDialog(Controller.getUsername());
        });


        return btnResetPassword;
    }

    private static JButton getLogoutBtn(JFrame frame) {
        JButton btnLogout = new JButton("Logout");

        btnLogout.addActionListener(e->{
            Controller.destroyCurrentUser();
            try {
                new HomeScreen(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return btnLogout;
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
