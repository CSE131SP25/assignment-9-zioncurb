package assignment9;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class BodySegment {
    private double x, y;
    private double size;

    public BodySegment(double x, double y, double size) {
    	 this.x = x;
    	 this.y = y;
    	 this.size = size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // pastel colors
    public void draw() {
        Color color = ColorUtils.PASTEL[(int) (Math.random() * ColorUtils.PASTEL.length)];
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, size);
    }
}



