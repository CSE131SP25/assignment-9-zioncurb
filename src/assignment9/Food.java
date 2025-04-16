package assignment9;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Food {
    private double x, y;
    private double size;
    private boolean isPowerUp;

    public Food(double size, boolean isPowerUp) {
        this.size = size;  // Ensure food uses the same size
        this.isPowerUp = isPowerUp;
        randomizePosition();
    }

    public void randomizePosition() {
        this.x = Math.random();  // Random X between 0 and 1
        this.y = Math.random();  // Random Y between 0 and 1
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

    public boolean isPowerUp() {
        return isPowerUp;
    }

    public void draw() {
        // Using pastel colors from the ColorUtils for consistency
        Color color = ColorUtils.getNextColor(); // Get the next pastel color in the cycle
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, size); // Draw food item as a circle with the color
    }
}





