import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener { //JPanel acts as CSS (In the HTML - CSS relationship)

    GamePanel(){ //This is a constructor of the GamePanel class
        //setBackground(Color.black);
    }

    public void startGame(){ //Method for starting game

    }

    public void paintComponent(Graphics g){ //Method for coloring?

    }

    public void draw(Graphics g){//Method for coloring?

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
