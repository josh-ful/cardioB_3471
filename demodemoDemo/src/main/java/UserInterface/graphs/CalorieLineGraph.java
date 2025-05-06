package UserInterface.graphs;

import javax.swing.*;

public class CalorieLineGraph extends LineGraph {
    CalorieLineGraph(JPanel panel, int goalSleep) {
        super(panel, goalSleep, "Calorie Intake", "Date", "Calorie Intake");
    }
}