package UserInterface.graphs;

import UserInterface.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LineGraph {
    public JPanel panel;
    public int goal;

    ArrayList<UserInterface.Point>points = readInCSV();
    LineGraph(JPanel jp, int goal, String title, String xAxisLabel, String yAxisLabel) {
        this.panel = jp;
        this.goal = goal;

        JFreeChart chart = makeLineChart(title, xAxisLabel, yAxisLabel );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.pink);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDismissDelay(Integer.MAX_VALUE);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel);
    }
    public JFreeChart makeLineChart(String title, String xAxisLabel, String yAxisLabel) {
        ArrayList<UserInterface.Point> points = readInCSV();
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        System.out.println(points.size());
        for(int i = 0; i < points.size(); i++) {
            categoryDataset.addValue((double)points.get(i).getY(), "1", ""+points.get(i).getX());
        }
        JFreeChart chart = ChartFactory.createLineChart(title, xAxisLabel, yAxisLabel, categoryDataset);
        ValueMarker marker = new ValueMarker(goal);  // position is the value on the axis
        marker.setPaint(Color.RED);
        marker.setLabel("GOAL"); // see JavaDoc for labels, colors, strokes

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        marker.setLabelBackgroundColor(Color.PINK);
        plot.addRangeMarker(marker);

        return chart;
    }
    /**
     *
     *
     * @param
     */
    public static ArrayList<UserInterface.Point> readInCSV() {
        ArrayList<UserInterface.Point>points = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader( new FileReader("src/main/java/UserInterface/dateAndWeight.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                points.add(new UserInterface.Point(Point.stringToDate(row[0]), Integer.parseInt(row[1])));
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return points;
    }
}
