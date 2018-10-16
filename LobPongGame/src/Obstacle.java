import java.awt.*;
import java.awt.Toolkit;

public class Obstacle {
	private static final double WIDTH = 10, HEIGHT = 10;
	private Game game;
	private double xPos = Math.random()* 600;
	private double yPos = Math.random() * 300;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image p;
    
	public Obstacle(Game game) {
		this.game = game;		
	    p =  toolkit.getImage("bomb.png").getScaledInstance(20, 20, 50);
	}



	public void set(int x, int y) {
		xPos= x;
		yPos = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)xPos,(int) yPos, (int)WIDTH, (int)HEIGHT);
	}

	public void paint(Graphics g) {
		g.drawImage(p, (int) xPos, (int) yPos, null);
	}
}