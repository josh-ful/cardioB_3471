/**
 * this class creates scene that shows a graph of calories
 */
package UserInterface.graphs;

import UserInterface.UserDailyMetricsGraphs;
import UserInterface.Scenes;

import javax.swing.*;
import java.awt.*;

public class CalorieGraphScene extends Scenes {
    GridBagConstraints c;
    /**
     * constructs a CalorieGraphScene object
     *
     */
    public CalorieGraphScene() {
        createAndShowGUI();
    }
    /**
     * sets layout of panel to GridBagLayout
     *
     */
    protected void panelLayout() {
        //panel.setSize(600, 600);
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    /**
     * creates gui of CalorieGraphScene
     *
     */
    @Override
    protected void createAndShowGUI() {
        super.createAndShowGUI();
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calorie Graph");

        panel.add(createBackButton(frame), c);
        int goalCalories = 1004;
        CalorieLineGraph f = new CalorieLineGraph(panel, goalCalories);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // TODO get class list and display
    }
    /**
     * adds button leading to previous scene
     *
     * @param frame JFrame which back button is displayed on
     */
    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(UserDailyMetricsGraphs.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 14;

        return button;
    }
}