/**
 * this class creates scene that shows a graph of the weights
 */
package UserInterface;

import javax.swing.*;
import java.awt.*;

public class WeightGraphScene extends Scenes {
    GridBagConstraints c;
    /**
     * constructs a WeightGraphScene object
     *
     * @param frame JFrame that weight graph is created on
     */
    public WeightGraphScene(JFrame frame) {
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

        DrawingPanel d = createLineChart();
        //panel.add(d, c);

        panel.add(createBackButton(frame), c);
       //WeightChart w = new WeightChart(panel);
        int goalWeight = 170;
        WeightLine f = new WeightLine(panel, goalWeight);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // TODO get class list and display
    }
    /**
     * creates a line chart using drawingPanel
     *
     */
    private DrawingPanel createLineChart() {
        DrawingPanel dPanel = new DrawingPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        //dPanel.paintComponents(panel.getGraphics());
        return dPanel;
    }
    /**
     * adds button leading to previous scene
     *
     * @param frame JFrame which back button is displayed on
     */
    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, UserDashboard.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 14;

        return button;
    }
}
