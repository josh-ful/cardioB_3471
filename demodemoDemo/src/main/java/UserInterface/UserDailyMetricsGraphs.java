/**
 * this class creates a scene that shows profile info
 */
package UserInterface;

import UserInformation.CurrentUser;
import UserInterface.addExercise.AddWeightDialog;
import UserInterface.graphs.CalorieGraphScene;
import UserInterface.graphs.SleepGraphScene;
import UserInterface.graphs.WeightGraphScene;
import UserInterface.MetricTypes;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class UserDailyMetricsGraphs extends Scenes{



    GridBagConstraints c;
    /**
     * Constructs a ProfileScreen object
     *
     * @param frame which scene is created on
     */
    public UserDailyMetricsGraphs(JFrame frame) {
        createAndShowGUI(frame);
    }
    /**
     * sets the panel layout to GridBagLayout and initializes
     * the GridBagConstraints
     *
     */
    protected void panelLayout() {
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    /**
     * creates a ProfileSreen using the super's createAndShowGUI
     * method and adds title with profile info
     * @param
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fitness Information");
        JLabel profilePageText = new JLabel("Today's Daily Health Metrics");
        profilePageText.setFont(new Font("Roboto", Font.BOLD, 40));

        frame.setLayout(new GridBagLayout());

        panel.add(profilePageText, c);
        panel.add(createNameLabel(), c);

        // TODO: don't want to pass frame to create method
        panel.add(createAddDailyMetric(frame), c);
        panel.add(weightGraphButton(frame), c);
        panel.add(sleepGraphButton(frame), c);
        panel.add(calorieGraphButton(frame), c);
        panel.add(createBackButton(frame), c);

        frame.add(panel);

        // TODO get metrics and display
    }

    /**
     * creates and positions name label
     *
     * @return JLabel containing name of user
     */
    private JLabel createNameLabel() {
        JLabel label = new JLabel("Name: " + CurrentUser.getName());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;

        return label;
    }

    /**
     * creates button to display scene to log weight
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton createAddDailyMetric(JFrame frame) {
        JButton button = new JButton("Add Daily Metrics");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;

        button.addActionListener(e -> {
            new InsertDailyMetrics(frame);
        });

        return button;
    }

    private JButton createAddSleepButton(JFrame frame) {
        JButton button = new JButton("Add Weight");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;

        button.addActionListener(e -> {
            new AddSleepDialog(frame);
        });

        return button;
    }


    private JButton createAddCaloriesButton(JFrame frame) {
        JButton button = new JButton("Add Weight");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;

        button.addActionListener(e -> {
            //new AddCaloriesDialog(frame);
        });

        return button;
    }


    private JButton createAddWorkoutButton(JFrame frame) {
        JButton button = new JButton("Add Weight");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;

        button.addActionListener(e -> {
            //new AddWorkoutDialog(frame);
        });

        return button;
    }
    /**
     * creates button to load and display previous scene
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton weightGraphButton(JFrame frame) {
        JButton button = new JButton("Weight Graph");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;

        button.addActionListener(e -> {
            new WeightGraphScene(frame);
        });

        return button;
    }

    /**
     * creates button to load and display previous scene
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton sleepGraphButton(JFrame frame) {
        JButton button = new JButton("Sleep Graph");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;

        button.addActionListener(e -> {
            new SleepGraphScene(frame);
        });

        return button;
    }
    /**
     * creates button to load and display previous scene
     *
     * @param frame which scene is created on
     * @return JButton with back button functionality
     */
    private JButton calorieGraphButton(JFrame frame) {
        JButton button = new JButton("Calorie Graph");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;

        button.addActionListener(e -> {
            new CalorieGraphScene(frame);
        });

        return button;
    }
    /**
     * back button repaints to previous scene
     *
     * @param frame JFrame which back button is added on
     */
    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, UserMainDash.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;

        return button;
    }

    /**
     * creates a new profileScreen
     *
     * @param frame which scene is created on
     *
     */
    public static void submittedNewScene(JFrame frame) {
        //refreshLogTable();
        new UserDailyMetricsGraphs(frame);
    }


}