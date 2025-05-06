/**
 * this class creates a point with x local date and
 * y int weight in pounds
 */
package UserInterface.graphs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Point {
    private Date x;
    private Double y;

    public Point() {}
    /**
     * constructs a point object
     *
     * @param l LocalDate
     * @param y int weight
     */
    public Point(Date l, Double y) {
        this.x = l;
        this.y = y;
    }
    /**
     * returns x local date
     *
     */
    public Date getX() {
        return x;
    }
    /**
     * returns y weight int
     *
     */
    public Double getY() {
        return y;
    }

    public void setX(Date x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    /**
     * returns x local date
     *
     * @param ogDate String to be converted into a localDate
     * @return LocalDate date
     */

   public static Date stringToDate(String ogDate) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date date = null;
       try{
           date = sdf.parse(ogDate);
       }catch(ParseException e){
           System.out.println("Error parsing date: " + ogDate);
       }
        return date;
    }
}
