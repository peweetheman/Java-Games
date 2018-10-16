import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Game extends JFrame{
	GamePanel panel = new GamePanel();
	protected int height = 500;
	protected int width= 500;
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	

	Game() {
		setTitle("The World's Greatest Game of Lob Pong");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setVisible(true);
	}

}
