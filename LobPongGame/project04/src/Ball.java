import java.awt.*;

import javax.swing.*;

public class Ball {
	private static final double WIDTH = 10, HEIGHT = 10;
	private Game game;
	private double xPos;
	private double yPos; 
	private double xMove;
	private double yMove;

	public Ball(Game game) {
		this.game = game;
		reset();
	}

	public void update() {
		yMove += .11;
		xPos +=  xMove;
		yPos +=  yMove;
		if (xPos < 0 || xPos > game.getWidth() - WIDTH - 15) {
			xMove=-xMove;
		}
		if(yPos<0) {
			yMove = -yMove;
		}
	}

	public void reset() {
		xPos= game.getWidth()/2 - 35;
		yPos = game.getHeight() - 100;
		do {
			xMove = 3*(Math.random()-.5);
		}
		while(((xMove) < 1 && xMove>-1));
		do {
			yMove = 10*(Math.random());
		}
		while(((int)(yMove) < 5));
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public double getxMove() {
		return xMove;
	}

	public void setxMove(double xMove) {
		this.xMove = xMove;
	}

	public double getyMove() {
		return yMove;
	}

	public void setyMove(double yMove) {
		this.yMove = yMove;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)xPos,(int) yPos, (int)WIDTH, (int)HEIGHT);
	}

	public void paint(Graphics g) {
		g.fillRect((int)xPos, (int)yPos, (int)WIDTH, (int)HEIGHT);
	}
	//public void 
}