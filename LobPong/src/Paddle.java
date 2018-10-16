import java.awt.Graphics;

public class Paddle {
	GamePanel game = new GamePanel();
	protected int posX = game.getWidth()/2;
	protected int posY = game.getHeight() + 30;
	protected int velX;
	protected int velY;
	protected int width = 25;
	protected int height = 5;
	public Paddle() {
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void update() {
		posX = posX + velX;
		posY = posY + velY;
	}

	public void pressed(int keyCode) {
		if (keyCode == 37) //left
			velX = 5;
		else if (keyCode == 39) //right
			velY = 5;
	}

	public void released(int keyCode) {
		if (keyCode == 37) {
			velX = 0;
		}
		else if (keyCode == 39) {
			velY = 0;
		}
	}
	public void paint(Graphics g) {
		g.fillRect(posX, posY, height, width);

	}
}
