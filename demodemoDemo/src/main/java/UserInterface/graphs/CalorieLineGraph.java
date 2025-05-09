package UserInterface.graphs;

import javax.swing.*;

public class CalorieLineGraph extends LineGraph {

    /**
     * constructs a CalorieLineGraph object
     *
     * @param panel JPanel
     * @param goalSleep int
     */
    CalorieLineGraph(JPanel panel, int goalSleep) {
        super(panel, goalSleep, "Calorie Intake", "Date",
                "Calorie Intake", "src/main/java/UserInterface/graphs/dateAndCalories.csv");
    }
}