package UserInterface;

import javax.swing.*;

import Controller.Controller;
import UserInformation.CurrentUser;
import UserInterface.Login.OnboardingDialog;

import java.awt.*;

public class Profile extends Scenes{
    private static JPanel metricsPanel;
    GridBagConstraints c;

    public Profile() {
        createAndShowGUI();
    }

    @Override
    protected void panelLayout() {
        //north center south layout
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    @Override
    protected void createAndShowGUI() {
        super.createAndShowGUI();
        //frame.setLocationRelativeTo(frame);
        panelLayout();

        //user info
        panel.add(makeUserInfoPanel(), BorderLayout.NORTH);

        //current metrics compared to goals
        metricsPanel = makeMetricsGoalsPanel();
        panel.add(metricsPanel, BorderLayout.CENTER);

        //buttons
        panel.add(makeButtonBar(frame), BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
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
        info.add(new JLabel(CurrentUser.getAge().toString()));

        info.add(new JLabel("Gender:"));
        info.add(new JLabel(CurrentUser.getGender()));

        info.add(new JLabel("Email:"));
        info.add(new JLabel(CurrentUser.getEmail()));

        return info;
    }

    private static JPanel makeMetricsGoalsPanel() {
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

    private JPanel makeButtonBar(JFrame frame) {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttons.add(getGoalsBtn(frame));
        buttons.add(getEditOnboardingBtn());
        buttons.add(getResetPasswordBtn());
        buttons.add(getLogoutBtn(frame));

        return buttons;
    }

    private JButton getGoalsBtn(JFrame frame) {
        JButton setGoals = new JButton("Set Goals");

        setGoals.addActionListener(e -> {
            GoalsDialog dlg = new GoalsDialog(frame);
            dlg.setVisible(true);
            panel.remove(metricsPanel);
            metricsPanel = makeMetricsGoalsPanel();
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
            new HomeScreen();
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
