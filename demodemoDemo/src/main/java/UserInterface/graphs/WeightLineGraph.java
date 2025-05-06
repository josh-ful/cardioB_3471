/**
 * this class creates a line graph of weight
 */
package UserInterface.graphs;

import javax.swing.*;

public class WeightLineGraph extends LineGraph {

    private int goal;
    /**
     * constructs a WeightLine objects
     *
     * @param panel JPanel
     */
    WeightLineGraph(JPanel panel, int goalWeight) {
        super(panel, goalWeight, "Weight Progress", "Date", "Weight(lbs)");

    }

}
