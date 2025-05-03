package UserInterface;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WeightBarGraph {
    WeightBarGraph(JPanel panel) {

       JFreeChart chart= makeChart();
       ChartPanel chartPanel = new ChartPanel(chart);
       chartPanel.setBackground(Color.pink);
       chartPanel.setFillZoomRectangle(true);
       chartPanel.setMouseWheelEnabled(true);
       chartPanel.setDismissDelay(Integer.MAX_VALUE);
       chartPanel.setPreferredSize(new Dimension(800, 600));
       panel.add(chartPanel);
    }
    public static JFreeChart makeChart(){
        ArrayList<Point> points = readInCSV();
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        for(int i = 0; i < points.size(); i++) {
            categoryDataset.addValue((double)points.get(i).getY(), "1", ""+points.get(i).getY());
        }
        JFreeChart chart = ChartFactory.createBarChart("Weight Progress", "Date", "Weight(lbs)", categoryDataset);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);
        chart.getLegend().setFrame(BlockBorder.NONE);


        return chart;
    }
    public static ArrayList<Point> readInCSV() {
        ArrayList<Point>points = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader( new FileReader("src/main/java/UserInterface/dateAndWeight.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                points.add(new Point(Point.stringToDate(row[0]), Integer.parseInt(row[1])));
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return points;
    }
}
