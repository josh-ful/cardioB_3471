package UserInterface;

import javax.swing.*;

import Controller.Controller;
import UserInformation.CurrentUser;
import UserInterface.Login.OnboardingDialog;

import java.awt.*;
import java.sql.SQLException;
/*
 * this class represents a Profile object
 * containing information about Profiles
 */
public class Profile extends Scenes{
    private static JPanel metricsPanel;
    GridBagConstraints c;

    private static JLabel currAge;
    private static JLabel currGender;
    private static JLabel currEmail;
    /**
     * constructs a Profile object
     *
     * @param frame JFrame
     */
    public Profile(JFrame frame) throws SQLException {
        createAndShowGUI(frame);
    }
    /**
     * sets panel layout to borderlayout
     *
     */
    @Override
    protected void panelLayout() {
        //north center south layout
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    /**
     * creates and displays gui
     *
     * @param frame JFrame
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        //frame.setLocationRelativeTo(frame);
        panelLayout();

        //user info
        panel.add(makeUserInfoPanel(), BorderLayout.NORTH);

        //current metrics compared to goals
        try {
            metricsPanel = makeMetricsGoalsPanel();
            panel.add(metricsPanel, BorderLayout.CENTER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //buttons
        panel.add(makeButtonBar1(frame), BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.pack();
        frame.setSize(FRAME_DIM);
        frame.setLocationRelativeTo(frame);
        frame.revalidate();
        frame.repaint();
    }
    /**
     * creates user info panel
     *
     * @return JPanel with user info on it
     */
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
    /**
     * creates user info panel
     *
     * @return JPanel with user info on it
     */
    public static void refreshInfoPanel(){
        currAge.setText(CurrentUser.getAge().toString());
        currGender.setText(CurrentUser.getGender());
        currEmail.setText(CurrentUser.getEmail());
    }
    /**
     * creates user goals panel
     *
     * @return JPanel
     */
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
    /**
     * makes button formatting to fit page better
     *
     * @return JPanel with buttons on it
     */
    private JPanel makeButtonBar1(JFrame frame) {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttons.add(getGoalsBtn(frame));
        buttons.add(getResetPasswordBtn());
        buttons.add(getLogoutBtn(frame));
        buttons.add(createBackButton(frame, UserMainDash.class));

        return buttons;
    }
    /**
     * creates the goals button
     *
     * @param frame JFrame
     * @return JButton
     */
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
    /**
     * makes button to edit onboarding information
     *
     * @return JButton
     */
    private static JButton getEditOnboardingBtn() {
        JButton btnEditOnboarding = new JButton("Edit Information");

        btnEditOnboarding.addActionListener(e->{
            new OnboardingDialog(true);
        });

        return btnEditOnboarding;
    }
    /**
     * makes button to reset password
     *
     * @return JButton
     */
    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");

        btnResetPassword.addActionListener(e->{
            new UserResetPasswordDialog(Controller.getUsername());
        });


        return btnResetPassword;
    }
    /**
     * makes button to log out of session
     *
     * @return JButton
     */
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
}
