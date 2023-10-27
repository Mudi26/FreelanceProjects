import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener { //JPanel acts as CSS (In the HTML - CSS relationship)


    static final int SCREEN_WIDTH = 600; //Constant variable that takes care of screen width
    static final int SCREEN_HEIGHT = 600; //Constant variable that takes care of screen height
    static final int UNIT_SIZE = 25; //Constant variable that takes care of how big the objects in the game are
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;//Determining how many objects can fit in the game
    static final int DELAY = 75; //The higher the number is, the more delay there is in the game
    final int x[] = new int[GAME_UNITS]; //This array and the one below, are going to keep track of all the
    final int y[] = new int[GAME_UNITS]; //coordinates of the snake's body, including the head. x and y coordinates.
    int bodyParts = 6; //initial amount of body parts for the snake
    int applesEaten; //How many eaten apples. Starts at 0
    int appleX; //The x coordinate of the apple. It's going to appear at random spots, each time a previous apple is eaten
    int appleY; //The y coordinate of the apple. It's going to appear at random spots, each time a previous apple is eaten
    char direction = 'R'; //The snake will start heading Right(R), Left(L), Up(U), Down(D)
    boolean running = false; //Whether the snake is active.
    Timer timer; //instance of the Timer class
    Random random; //instance of the Random class

    GamePanel() { //This is a constructor of the GamePanel class
        random = new Random();// Create a new Random object for generating random numbers.
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);// You can call methods on "this" to perform actions.
        this.setFocusable(true); // Allow this component to receive focus for keyboard input.
        this.addKeyListener(new MyKeyAdapter());//a key listener is added, which will listen for keyboard input using the MyKeyAdapter class.

        startGame();

    }

    public void startGame() { //Method for starting game
        newApple(); //Putting the apple on the screen
        running = true; // Set the game state to running, indicating the snake is active
        timer = new Timer(DELAY, this); // Create a Timer instance with the specified delay and this object as the ActionListener
        // The listener performs the actionPerformed every DELAY(75) milliseconds.
        // Meaning it will focus on whether there has been a change in the game, example: direction, so it can turn the snake
        // "this", means this instance of the GamePanel, if there was a new game, it has its own new instance.
        timer.start(); // Start the timer, which will trigger the actionPerformed method
    }

    public void paintComponent(Graphics g) { // Method for rendering graphics on the panel
        super.paintComponent(g); // Call the superclass's paintComponent method to clear previous drawings and prepare for new rendering
        draw(g); // Call the custom draw method to render the current game graphics using the Graphics object 'g'
    }


    public void draw(Graphics g) { //Method for creating visible things like, a grid,an apple.
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); //Creating matrixes, so every unit can be seen (x)
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE); //Creating matrixes, so every unit can be seen (y)
        }
        g.setColor(Color.red); //Coloring the apple
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //Creating it as a circle

        for (int i = 0; i < bodyParts; i++) { // Loop through the body parts of the snake
            if (i == 0) { // If this is the head of the snake
                g.setColor(Color.green); // Set the color to green
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // Draw a green rectangle representing the head
            } else { // For all other body parts (tail)
                g.setColor(new Color(45, 180, 0)); // Set a color similar to green, but different from the head of the snake
                // Draw a rectangle representing the body part (tail) in a slightly different color
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }

    }

    public void newApple() { //Method for implementing a new apple
        // Generate random coordinates for the apple, ensuring it aligns with the grid (multiples of UNIT_SIZE)
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() { // Iterate through all the body parts of the snake
        for (int i = bodyParts; i > 0; i--) { //To iterate through all the bodyparts of the snake
            x[i] = x[i - 1]; // Move the body part to the position of the previous body part
            y[i] = y[i - 1];
        }
        // Move the head of the snake based on the current direction
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE; // Move up by subtracting from the y-coordinate
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE; // Move down by adding to the y-coordinate
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE; // Move left by subtracting from the x-coordinate
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE; // Move right by adding to the x-coordinate
                break;
        }
    }

    public void checkApple() { //Method for checking if apple is present?

    }

    public void checkCollisions() { //Method for if snake hits something it shouldn't
        //To check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == y[i]) && (y[0] == y[i])) {
                running = false;
            }
            //To check if head collides with left side
            if (x[0] < 0) {
                running = false;
            }
            //To check if head collides with right side
            if (x[0] > SCREEN_WIDTH) {
                running = false;
            }
            //To check if head collides with top
            if (y[0] < 0) {
                running = false;
            }
            //To check if head collides with the bottom
            if (y[0] > SCREEN_HEIGHT) {
                running = false;
            }
            if (!running) {
                timer.stop();
            }
        }
    }

    public void gameOver(Graphics g) { //Method for game over

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) { // If the game is currently running
            move(); // Move the snake
            checkApple(); // Check for collisions with the apple
            checkCollisions(); // Check for collisions with walls or the snake's own body
        }
        repaint(); // Trigger a repaint of the game panel
    }

    public class MyKeyAdapter extends KeyAdapter { // This is an inner class that extends KeyAdapter

        @Override
        public void keyPressed(KeyEvent e) { // When a key is pressed, this method is called
            switch (e.getKeyCode()) { // Determine which key was pressed based on its keycode
                case KeyEvent.VK_LEFT: // If the left arrow key is pressed
                    if (direction != 'R') { // If the snake is not currently moving right
                        direction = 'L'; // Set the direction to 'L' (left)
                    }
                    break;
                case KeyEvent.VK_RIGHT: // If the right arrow key is pressed
                    if (direction != 'L') { // If the snake is not currently moving left
                        direction = 'R'; // Set the direction to 'R' (right)
                    }
                    break;
                case KeyEvent.VK_UP: // If the up arrow key is pressed
                    if (direction != 'D') { // If the snake is not currently moving down
                        direction = 'U'; // Set the direction to 'U' (up)
                    }
                    break;
                case KeyEvent.VK_DOWN: // If the down arrow key is pressed
                    if (direction != 'U') { // If the snake is not currently moving up
                        direction = 'D'; // Set the direction to 'D' (down)
                    }
                    break;
            }
        }
    }
}
