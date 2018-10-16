import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	private Game game;
	private Ball ball;
	private Paddle paddle;
	PowerUp p1;
	PowerUp p2;
	Bomb b1;
	Bomb b2;
	Bomb b3;
	ScoreManager manage = new ScoreManager();
	private int score = 0;
	private double time = 10;
	private double levelTime = 10;
	private int lives = 3;
	private int level = 1;
	Timer timer;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image1;
	Image image2;
	Image image3;
	Image image4;
	Image image5;
	Image image6;
	boolean intro = true;
	int i =0;

	public GamePanel(Game game) {
		setBackground(Color.WHITE);
		this.game = game;
		paddle = new Paddle(game);
		ball = new Ball(game);
		timer = new Timer(10, this);
		p1 = new PowerUp(game);
		p2 = new PowerUp(game);
		b1 = new Bomb(game, ball);
		b2 = new Bomb(game, ball);
		b3 = new Bomb(game, ball);
		addKeyListener(this);
		setFocusable(true);
		image1 = getToolkit().getImage("MAC_Space_Background.jpg").getScaledInstance(game.getWidth()+30, game.getHeight()+500, 100);
		image2 = getToolkit().getImage("image2.png").getScaledInstance(game.getWidth(), game.getHeight(), 100); //Images from iconarchive.com
		image3 = getToolkit().getImage("image3.jpg").getScaledInstance(game.getWidth(), game.getHeight(), 100);
		image4 = getToolkit().getImage("image4.jpg").getScaledInstance(game.getWidth(), game.getHeight(), 100);
		image5 = getToolkit().getImage("image5.jpg").getScaledInstance(game.getWidth(), game.getHeight(), 100);
		image6 = getToolkit().getImage("image6.jpg").getScaledInstance(game.getWidth(), game.getHeight(), 100);
	}

	public void catchBall() {
	}

	public void increaseScore(int x) {
		score+=x;
	}

	public int getScore() {
		return score;
	}

	private void update() {
		ball.update();
		paddle.update();

	}

	public void actionPerformed(ActionEvent e) {
		time-=.02;
		paddle.setPlaying(true);
		if (ball.getBounds().intersects(paddle.getBounds())) {
			ball.setyMove(-ball.getyMove());
			ball.setyPos(ball.getyPos() - 3);
			increaseScore(1);
		}
		if ((level != 1 && level != 2) && (b1.getBounds().intersects(ball.getBounds()) ||b2.getBounds().intersects(ball.getBounds()) || b3.getBounds().intersects(ball.getBounds()))) {
			lose();
		}
		if(ball.getBounds().intersects(p1.getBounds()) && level !=1) {
			paddle.setHyper(paddle.getHyper()*2);
			p1.set();
		}
		if(ball.getBounds().intersects(p2.getBounds())&& level!=1) {
			paddle.setHyper(2);
			p2.set();
		}
		if(ball.getyPos()>game.getHeight()) {
			lose();
		}
		if(time < 1) {
			levelUp();
		}
		if(paddle.isPlaying()) {
			update();
			repaint();
		}


	}
	public void levelUp() {
		JOptionPane.showMessageDialog(game, "LEVEL UP!");
		paddle.setPlaying(false);
		ball.reset();
		paddle.reset();
		increaseScore(10);
		levelTime+=10;
		time = levelTime;
		level++;
		timer.stop();
		repaint();
		b1.set();
		b2.set();
		b3.set();
		p1.set();
		p2.set();
	}
	public void lose() {
		paddle.setPlaying(false);
		paddle.reset();
		ball.reset();
		time = levelTime;
		lives--;
		b1.set();
		b2.set();
		b3.set();
		p1.set();
		p2.set();
		if ( lives == 0) {
			String name = JOptionPane.showInputDialog(game, "Enter your name", "Score", 1);
			manage.addScore(name, score);
			JOptionPane.showMessageDialog(game, manage.highscores() );
			System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(game, "You Lost a life...");
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		paddle.pressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		paddle.released(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
		intro = false;
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		toolkit.sync();
		Font top = new Font("Red", 15, 20);
		Font intr = new Font("intr", 10, 20);
		g.setFont(intr);
		if(!timer.isRunning() && !intro) {
			g.setColor(Color.BLUE);
			g.drawString("Level: " + level + "  Level time: " + (int) levelTime + "s", game.getWidth()/3, game.getHeight()/2-80);
			if(level == 2) {
				g.drawString("Hit the power ups to double your speed!", game.getWidth()/3, game.getHeight()/2-100);
			}
			if(level == 3) {
				g.drawString("Watch out for bombs!", game.getWidth()/3, game.getHeight()/2-100);
			}
		}
		switch(level) {
		case 1: g.drawImage(image1, -30, -500, null);
		break;
		case 2: g.drawImage(image2, 0, 0, null);
		p1.paint(g);
		p2.paint(g);
		break;
		case 3: g.drawImage(image3, 0, 0, null);
		p1.paint(g);
		p2.paint(g);
		b1.paint(g);
		b2.paint(g);
		b3.paint(g);
		break;
		case 4: g.drawImage(image4, 0, 0, null);
		p1.paint(g);
		p2.paint(g);
		b1.paint(g);
		b2.paint(g);
		b3.paint(g);
		break;
		case 5: g.drawImage(image5, 0, 0, null);
		p1.paint(g);
		p2.paint(g);
		b1.paint(g);
		b2.paint(g);
		b3.paint(g);
		break;
		case 6: g.drawImage(image6, 0, 0, null);
		p1.paint(g);
		p2.paint(g);
		b1.paint(g);
		b2.paint(g);
		b3.paint(g);
		break;
		case 7: g.drawString("GOOD FOR YOU I GUESS?", game.getWidth()/4, game.getHeight()/2-80);
		}
		g.setColor(Color.DARK_GRAY);
		if(intro) {
			g.drawString("Welcome to Lob Pong ", game.getWidth()/4, game.getHeight()/2-80);
			g.drawString("Use arrow keys to move ", game.getWidth()/4, game.getHeight()/2-60);
			g.drawString("Score 1 point for every bounce and 10 for every level", game.getWidth()/4, game.getHeight()/2-40);
			g.drawString("Press any key to start! ", game.getWidth()/4, game.getHeight()/2-20);
		}
		else {
			g.setFont(top);
			g.drawString("Level: " + level + "   Lives: " + lives + "     Time: " + (int) time + "   Score: " + (game.getPanel().getScore()), game.getWidth()/5, 30);
		}
		ball.paint(g);
		paddle.paint(g);

	}
}