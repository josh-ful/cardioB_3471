/**
 * this class creates scene that shows a graph of the weights
 */
package UserInterface.graphs;

import UserInterface.UserDailyMetricsGraphs;
import UserInterface.Scenes;

import javax.swing.*;
import java.awt.*;

public class WeightGraphScene extends Scenes {
    GridBagConstraints c;
    /**
     * constructs a WeightGraphScene object
     *
     */
    public WeightGraphScene() {
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
     * creates gui of weightGraphScene
     *
     */
    @Override
    protected void createAndShowGUI() {
        super.createAndShowGUI();
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Weight Graph");

        panel.add(createBackButton(frame), c);
        int goalWeight = 170;
        WeightLineGraph f = new WeightLineGraph(panel, goalWeight);

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
