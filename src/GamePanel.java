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
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;//Determining how many objects can fit in the game
    static final int DELAY = 75; //The higher the number is, the more delay there is in the game
    final int x[] = new int[GAME_UNITS]; //This array and the one below, are going to keep track of all the
    final int y[] = new int[GAME_UNITS]; //coordinates of the snake's body, including the head. x and y coordinates.
    int bodyParts=6; //initial amount of body parts for the snake
    int applesEaten; //How many eaten apples. Starts at 0
    int appleX; //The x coordinate of the apple. It's going to appear at random spots, each time a previous apple is eaten
    int appleY; //The y coordinate of the apple. It's going to appear at random spots, each time a previous apple is eaten
    char direction = 'R'; //The snake will start heading Right(R), Left(L), Up(U), Down(D)
    boolean running = false; //Whether the snake is active.
    Timer timer; //instance of the Timer class
    Random random; //instance of the Random class
    GamePanel(){ //This is a constructor of the GamePanel class
        random = new Random();// Create a new Random object for generating random numbers.
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);// You can call methods on "this" to perform actions.
        this.setFocusable(true); // Allow this component to receive focus for keyboard input.
        this.addKeyListener(new MyKeyAdapter());//a key listener is added, which will listen for keyboard input using the MyKeyAdapter class.

        startGame();

    }

    public void startGame(){ //Method for starting game
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


    public void draw(Graphics g){ //Method for creating visible things like, a grid,an apple.
        for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE, SCREEN_HEIGHT); //Creating matrixes, so every unit can be seen (x)
            g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE); //Creating matrixes, so every unit can be seen (y)
        }
        g.setColor(Color.red); //Coloring the apple
        g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE); //Creating it as a circle
    }
    public void newApple(){ //Method for implementing a new apple
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; // x coordinate for our apple
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE; // x coordinate for our apple

    }

    public void move(){ //Method for moving

    }

    public void checkApple(){ //Method for checking if apple is present?

    }

    public void checkCollisions(){ //Method for if snake hits something it shouldn't

    }

    public void gameOver(Graphics g){ //Method for game over

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class MyKeyAdapter extends KeyAdapter{ //This is an inner class
        @Override
        public void keyPressed(KeyEvent e){ //when pressing a key, this method is used

        }

    }
}
