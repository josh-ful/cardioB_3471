/**
 * this class creates scene that shows a graph of the weights
 */
package UserInterface.graphs;

import UserInterface.UserDailyMetricsGraphs;
import UserInterface.Scenes;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class WeightGraphScene extends Scenes {
    GridBagConstraints c;
    /**
     * constructs a WeightGraphScene object
     *
     * @param frame JFrame that weight graph is created on
     */
    public WeightGraphScene(JFrame frame) throws SQLException {
        createAndShowGUI(frame);
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
     * @param frame JFrame which the gui will be created on
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Weight Graph");

        panel.add(createBackButton(frame), c);
        int goalWeight = 170;
        WeightLineGraph f = new WeightLineGraph(panel, goalWeight);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * adds button leading to previous scene
     *
     * @param frame JFrame which back button is displayed on
     */
    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, UserDailyMetricsGraphs.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 14;

        return button;
    }
}
