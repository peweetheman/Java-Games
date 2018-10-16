import java.awt.Graphics;

public class Ball {
	protected int posX;
	protected int posY;
	protected int velX;
	protected int velY;
	protected int height = 5;
	protected int width = 5;
	GamePanel game = new GamePanel();

	public Ball() {
	}
	public Ball(int posX, int posY, int velX, int velY) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
	}

	public int getPosX() {
		posX = posX - velX;
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		posY = posY - velY;
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}

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
	public void update() {
		posX = posX + velX;
		posY = posY + velY;
		velY = velY - 3;
	}
	public void checkCollision() {
		if(posY == game.getHeight()) {
			System.out.println("THATS AN L");
		}
		else if (posX == game.getWidth()-this.width) {
			velX = -velX;
		}
		else if (posY == 0 -this.height) {
			velY = - velY;
		}
	}
	public void paint(Graphics g) {
		g.fillOval(posX, posY, width, height);

	}
}

