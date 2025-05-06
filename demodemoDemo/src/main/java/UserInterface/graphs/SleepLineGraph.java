package UserInterface.graphs;

import javax.swing.*;

public class SleepLineGraph extends LineGraph {
    SleepLineGraph(JPanel panel, int goalSleep) {
        super(panel, goalSleep, "Sleep Progress", "Date", "Hours of Sleep", "src/main/java/UserInterface/graphs/dateAndSleep.csv");
    }
}
