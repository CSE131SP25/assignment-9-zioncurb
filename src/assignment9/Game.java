package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;


public class Game {

    private Snake snake;
    private Food food;
    private final double FOOD_SIZE = Snake.SEGMENT_SIZE;
    private int score = 0;
    private int snakeSpeed = 100; 
    private int slowTimer = 0; //slow timer!

    public Game() {
        StdDraw.enableDoubleBuffering();

        // Create a new Snake and a piece of Food
        snake = new Snake();
        food = new Food(FOOD_SIZE, Math.random() < 0.2);

    }

    public void play() {
     
        while (true) {
            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }

            snake.move(); 

            // checks if snake eats food/ power-up
            if (snake.eatFood(food)) {
                score++;

                if (food.isPowerUp()) {
                    snakeSpeed = 200;     
                    slowTimer = 30; 
                    
                }

                // Re-generate food 
                food.randomizePosition();
                food = new Food(FOOD_SIZE, Math.random() < 0.5);
            }

            if (!snake.isInBounds()) {
                System.out.println("Game Over! Snake went out of bounds.");
                break;
            }

            updateDrawing(); 
            
            if (slowTimer > 0) {
                slowTimer--;
                if (slowTimer == 0) {
                    snakeSpeed = 100; // reset speed
                }
            }

            StdDraw.pause(snakeSpeed);
        }
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1; //up
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2; //down
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3; //left
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4; //right
        }
        return -1;
    }

    /**
     * Clears the screen, draws the snake and food, pauses, and shows the content
     */
    private void updateDrawing() {
    	StdDraw.clear();
        snake.draw();
        food.draw();

     
        StdDraw.setPenColor(0,0,0);
        StdDraw.textLeft(0.02, 0.98, "Snake Game!");
        StdDraw.textLeft(0.02,0.93, "Score: " + score);
        StdDraw.show();
    
    if (slowTimer > 0) {
        StdDraw.setPenColor(0, 0, 200);
        StdDraw.text(0.5, 0.95, "SLOW MODE!");
    }
    StdDraw.show();
    }
    public static void main(String[] args) {
        Game g = new Game();
        g.play(); // Start the game
    }
}







