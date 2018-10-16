import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    private int WIDTH = 60, HEIGHT = 5;
    private Game game;
    private int xPos;
    private int yPos;
    private int paddleMove;
    private int hyper = 1;
    private boolean playing = true;

	public Paddle(Game game) {
        this.game = game;
        reset();
    }
	
    public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getPaddleMove() {
		return paddleMove;
	}

	public void setHyper(int hyper) {
		this.hyper = hyper;
	}

	public int getHyper() {
		return hyper;
	}
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

    public void update() {
        if (xPos > 0 && xPos < game.getWidth() - WIDTH - 15)
            xPos += paddleMove;
        else if (xPos <= 0)
            xPos+=2;
        else if (xPos >= game.getWidth() - WIDTH- 15)
            xPos-=2;
    }

    public void pressed(int keyCode) {
    	if(playing) {
        if (keyCode ==  KeyEvent.VK_RIGHT) {
            paddleMove = 2 * hyper;
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            paddleMove = -2 * hyper;
        }
    	}
    }

    public void released(int keyCode) {
        if (keyCode ==  KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
            paddleMove = 0;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(xPos, yPos, WIDTH, HEIGHT);
    }
    
    public void reset() {
    	xPos= game.getWidth()/2-WIDTH;
        yPos = game.getHeight()-80;
         paddleMove = 0;
    }
}