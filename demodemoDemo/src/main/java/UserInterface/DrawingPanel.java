package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    DrawingPanel() {}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(450, 700);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        //System.out.println(getWidth());
        //System.out.println(getHeight());
        g.fillRect(0, 0, 20, 20);
        g.setColor(Color.BLACK);

        ArrayList<Point> points = readInCSV();
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        g2d.drawLine(50, height-50, width-50, height-50);
        g2d.drawLine(50, height-50, 50, 50);

        double xScale = (double)(width-100)/points.size();
        System.out.println(xScale);
        double yScale = (double)(height-100)%points.size();
        System.out.println(yScale);
        System.out.println(points.size());
        for(int i = 0; i < points.size()-1; i++) {
            int x1 = 9;//(int)((double)points.get(i).getX()/10 * xScale);
            System.out.print(x1 + " ");
            int y1 = (int)(800 - (2.5*(points.get(i).getY() - 80) + 50));
            System.out.print(y1 + " ");
            int x2 = 6;//(int)(50 + ((double)points.get(i+1).getX()/10) * xScale);
            System.out.print(x2 + " ");
            int y2 = (int)(800 - (2.5*(points.get(i+1).getY() - 80) + 50));
            System.out.println(y2);
            g2d.drawLine(x1, y1, x2, y2);
        }
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
