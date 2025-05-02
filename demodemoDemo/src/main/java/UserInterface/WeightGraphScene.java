package UserInterface;

import javax.swing.*;
import java.awt.*;

public class WeightGraphScene extends Scenes {
    GridBagConstraints c;
    public WeightGraphScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    protected void panelLayout() {
        //panel.setSize(600, 600);
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Weight Graph");

        DrawingPanel d = createLineChart(frame);
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

    private DrawingPanel createLineChart(JFrame frame) {
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

    private JButton createBackButton(JFrame frame) {
        JButton button = super.createBackButton(frame, ProfileScreen.class);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 14;

        return button;
    }
}
