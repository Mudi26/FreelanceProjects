import javax.swing.*;

public class GameFrame extends JFrame { //JFrame acts as HTML (In the HTML - CSS relationship)

    GameFrame(){ //This is a constructor of the GameFrame class

        /*GamePanel panel = new GamePanel(); //Create a new instance of the GamePanel class
        this.add(panel); Shortcut down below */

        this.add(new GamePanel()); // Add the GamePanel to this GameFrame (the current JFrame)
        this.setTitle("Snake");    // Set the title of the game window to "Snake"
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the application when the window is closed
        this.setResizable(false); // Disable the ability to resize the game window
        this.pack(); // Automatically adjust the size of the window to fit its contents
        this.setVisible(true); // Make the game window visible
        this.setLocationRelativeTo(null); // Center the game window on the screen
    }
}
