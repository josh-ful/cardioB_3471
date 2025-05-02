package UserInterface;

import java.time.LocalDate;

public class Point {
    private LocalDate x;
    private int y;
    public Point(LocalDate l, int y) {
        this.x = l;
        this.y = y;
    }
    public LocalDate getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static LocalDate stringToDate(String ogDate) {
        LocalDate date = LocalDate.parse(ogDate);
        return date;
    }
}
