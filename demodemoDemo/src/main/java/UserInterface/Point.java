/**
 * this class creates a point with x local date and
 * y int weight in pounds
 */
package UserInterface;

import java.time.LocalDate;

public class Point {
    private LocalDate x;
    private int y;
    /**
     * constructs a point object
     *
     * @param l LocalDate
     * @param y int weight
     */
    public Point(LocalDate l, int y) {
        this.x = l;
        this.y = y;
    }
    /**
     * returns x local date
     *
     */
    public LocalDate getX() {
        return x;
    }
    /**
     * returns y weight int
     *
     */
    public int getY() {
        return y;
    }
    /**
     * returns x local date
     *
     * @param ogDate String to be converted into a localDate
     * @return LocalDate date
     */
    public static LocalDate stringToDate(String ogDate) {
        LocalDate date = LocalDate.parse(ogDate);
        return date;
    }
}
