import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
    private final static int WIDTH = 700, HEIGHT = 400;
    private GamePanel panel;

    public Game() {
        setSize(WIDTH, HEIGHT);
        setTitle("The Greatest Game of Lob Pong");
        setBackground(Color.WHITE);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new GamePanel(this);
        add(panel);
        
    }

    public void newGame() {
    	Game game = new Game();
    }
    public GamePanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        new Game();
    }
}