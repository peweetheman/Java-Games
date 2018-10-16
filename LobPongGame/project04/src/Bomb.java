import java.awt.*;

public class Bomb {
	private static final double WIDTH = 15, HEIGHT = 15;
	private Game game;
	private Ball ball;
	private double xPos;
	private double yPos;
	private static boolean explode;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image p;
	double i = 0;

	public Bomb(Game game, Ball ball) {
		this.game = game;	
		this.ball = ball;
		set();
		p =  toolkit.getImage("bomb.png").getScaledInstance(20, 20, 100);
	}


	public static void setExplode(boolean ex) {
		explode = ex;
	}

	public void set() {
		xPos = Math.random()* (500)+50;
		yPos = Math.random() * (200)+50;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)xPos,(int) yPos, (int)WIDTH, (int)HEIGHT);
	}

	public void paint(Graphics g) {
		g.drawImage(p, (int) xPos, (int) yPos, null);
		
}
}