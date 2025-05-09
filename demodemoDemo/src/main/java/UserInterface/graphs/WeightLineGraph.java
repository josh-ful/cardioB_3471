/**
 * this class creates a line graph of weight
 */
package UserInterface.graphs;

import javax.swing.*;

public class WeightLineGraph extends LineGraph {

    private int goal;
    /**
     * constructs a WeightLineGraph object
     *
     * @param panel JPanel
     * @param goalWeight int
     */
    WeightLineGraph(JPanel panel, int goalWeight) {
        super(panel, goalWeight, "Weight Progress", "Date",
                "Weight(lbs)", "src/main/java/UserInterface/graphs/dateAndWeight.csv");

    }

}
