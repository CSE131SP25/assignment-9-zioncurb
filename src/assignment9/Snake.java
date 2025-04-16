package assignment9;

import java.awt.Color;
import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	public static final double SEGMENT_SIZE = 0.03;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;
    private int lastDirection; // To prevent reversing direction

    public Snake() {
        segments = new LinkedList<>();
        // Initial body segment (example: a snake starts with one segment)
        BodySegment head = new BodySegment(0.5, 0.5, SEGMENT_SIZE); // Starting position in the middle
        segments.add(head);
        deltaX = 0;
        deltaY = 0;
        lastDirection = -1; // Initial direction, undefined
    }

    public void changeDirection(int direction) {
        // Prevent reversing direction
        if ((direction == 1 && lastDirection != 2) || 
            (direction == 2 && lastDirection != 1) || 
            (direction == 3 && lastDirection != 4) || 
            (direction == 4 && lastDirection != 3)) {
            if (direction == 1) { // up
                deltaY = MOVEMENT_SIZE;
                deltaX = 0;
            } else if (direction == 2) { // down
                deltaY = -MOVEMENT_SIZE;
                deltaX = 0;
            } else if (direction == 3) { // left
                deltaY = 0;
                deltaX = -MOVEMENT_SIZE;
            } else if (direction == 4) { // right
                deltaY = 0;
                deltaX = MOVEMENT_SIZE;
            }
            lastDirection = direction;
        }
    }

    /**
     * Moves the snake by updating the position of each of the segments
     * based on the current direction of travel
     */
    public void move() {
        // Move the body segments (start from the tail and move to the front)
        for (int i = segments.size() - 1; i > 0; i--) {
            BodySegment current = segments.get(i);
            BodySegment previous = segments.get(i - 1);
            current.setPosition(previous.getX(), previous.getY()); // Update position based on the segment in front
        }

        // Move the head (the first segment) based on direction
        BodySegment head = segments.get(0);
        head.setPosition(head.getX() + deltaX, head.getY() + deltaY);
    }

    /**
     * Draws the snake by drawing each segment with pastel colors
     */
    public void draw() {
        for (int i = 0; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            // Randomly pick a pastel color for each body segment
            Color color = ColorUtils.PASTEL[i % ColorUtils.PASTEL.length]; // Ensure color cycles through pastel array
            StdDraw.setPenColor(color); // Set the color for the current segment
            segment.draw(); // Draw the segment with the chosen color
        }
    }

    /**
     * The snake attempts to eat the given food, growing if it does so successfully
     * @param f the food to be eaten
     * @return true if the snake successfully ate the food
     */
    public boolean eatFood(Food f) {
        BodySegment head = segments.get(0);
        double dx = head.getX() - f.getX();
        double dy = head.getY() - f.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // collide
        if (distance <= SEGMENT_SIZE) {
            // add segment
            BodySegment newSegment = new BodySegment(segments.getLast().getX(), segments.getLast().getY(), SEGMENT_SIZE);
            segments.addLast(newSegment);
            return true;
        }
        return false;
    }



    /**
     * Returns true if the head of the snake is in bounds
     * @return whether or not the head is in the bounds of the window
     */
    public boolean isInBounds() {
        BodySegment head = segments.get(0);
        return head.getX() >= 0 && head.getX() <= 1 && head.getY() >= 0 && head.getY() <= 1;
    }

    /**
     * Checks if the snake collides with itself
     * @return true if the snake's head collides with any other body segment
     */
    public boolean collidesWithSelf() {
        BodySegment head = segments.get(0);
        for (int i = 1; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            double dx = head.getX() - segment.getX();
            double dy = head.getY() - segment.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            // If the head is too close to any part of the body, it's a collision
            if (distance < SEGMENT_SIZE / 2) {
                return true;
            }
        }
        return false;
    }
}






