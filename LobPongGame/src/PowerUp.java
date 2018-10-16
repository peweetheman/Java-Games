import java.awt.*;
import java.awt.Toolkit;

public class PowerUp {
	private static final double WIDTH = 24, HEIGHT = 24;
	private Game game;
	private double xPos;
	private double yPos;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image p;

	public PowerUp(Game game) {
		this.game = game;		
		set();
		p =  toolkit.getImage("powerUp.png").getScaledInstance(30, 30, 100);
	}



	public void set() {
		xPos = Math.random()* 500 + 50;
		yPos = Math.random() * 200 + 50;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)xPos,(int) yPos, (int)WIDTH, (int)HEIGHT);
	}

	public void paint(Graphics g) {
		g.drawImage(p, (int) xPos, (int) yPos, null);
	}
}