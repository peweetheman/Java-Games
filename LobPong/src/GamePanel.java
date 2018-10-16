import java.awt.Graphics;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePanel extends JPanel {

	Ball ball = new Ball();
	Paddle paddle = new Paddle();
	Game game = new Game();

	private Timer timer = new Timer(27, new TimerCallback1()); 
	private JButton button1;

	GamePanel() {
		setSize(game.getWidth(), game.getHeight());
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ball.paint(g);
		paddle.paint(g);

	}

	double timeConstant=.007;
	protected class TimerCallback1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			timeConstant+=.007;
			ball.update();
			paddle.update();
			repaint();
		}
	}
}