import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Canvas extends JFrame{

	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		canvas.setVisible(true);
	}

	private Timer timer = new Timer(27, new TimerCallback1()); //1000 = 1 second First timer to keep track of motion
	private Timer timer2 = new Timer(100, new TimerCallback2()); //Second timer to keep track of explosion
	private JButton button1; //Each of these is a firework type
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button6;
	private JButton button7;
	private JButton extraCreditSuperSpecialFirework;
	private JButton buttonReset; 
	private JPanel westPanel; //This is the Firework controller panel
	private JLabel angleLabel;
	private JLabel speedLabel;
	private JLabel getTime;
	private JLabel title;
	private JLabel xlabel;
	private JLabel ylabel;
	private JSlider anglePrompt;
	private JSlider speedPrompt;
	private JSlider startx;
	private JSlider starty;
	private JButton fire;
	private JSlider timeField;
	private double launchAngle=45;
	private double speed=50;
	protected int delay=2;
	protected double time=0.05;
	private final double GRAVITY = 9.81;
	private double X = 220;
	private double Y = 400;
	double Xtraject = X; //used for extra credit trajectory calculations
	double Ytraject = Y;
	double explosionComponentX;
	double explosionComponentY;
	private double explodeX; //This value is to keep track of center of explosion
	private double explodeY; //This value is to keep track of center of explosion
	private int i = 0;
	private int firework = 1;

	Paint painter = new Paint();
	Graphics g = painter.getGraphics();


	public Canvas() {
		setTitle("Fireworks Project");
		setVisible(true);
		setSize(800, 600);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); //JFrame now has a BorderLayout

		westPanel = new JPanel();
		add(painter, BorderLayout.EAST);

		add(westPanel, BorderLayout.WEST); //westPanel is in BorderLayout.WEST
		angleLabel = new JLabel("Enter an angle (0 to 180): ");
		speedLabel = new JLabel("Enter a speed (20-100): ");
		getTime = new JLabel("Enter time to explosion (1s-5s): ");
		xlabel = new JLabel("      Initial X ");
		ylabel = new JLabel("      Initial Y ");
		xlabel.setPreferredSize(new Dimension (80, 30));
		ylabel.setPreferredSize(new Dimension (80, 30));
		startx = new JSlider(5, 1000);
		startx.setValue(5);
		starty= new JSlider(100, 800);
		starty.setValue(400);
		startx.setPreferredSize(new Dimension (80, 30));
		starty.setPreferredSize(new Dimension (80, 30));
		anglePrompt = new JSlider(0, 180);
		speedPrompt = new JSlider(20, 100);
		timeField = new JSlider(1, 5);
		anglePrompt.setValue(45);
		timeField.setValue(2);
		title = new JLabel("  FIREWORK CONTROLLER  ");
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(title);
		westPanel.add(xlabel);
		westPanel.add(ylabel);
		westPanel.add(startx);
		westPanel.add(starty);
		westPanel.add(angleLabel);
		westPanel.add(anglePrompt);
		westPanel.add(speedLabel);
		westPanel.add(speedPrompt);
		westPanel.add(getTime);
		westPanel.add(timeField);
		startx.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				X = (startx.getValue())+214;
				paint(g);
			}
		}); 
		starty.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Y = (starty.getValue());
				paint(g);
			}
		}); 
		speedPrompt.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speed = (speedPrompt.getValue());
				System.out.println("Speed: " + speed);
			}
		}); 
		anglePrompt.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				launchAngle = (anglePrompt.getValue());
				System.out.println("LaunchAngle: " + launchAngle);
			}
		});
		speedPrompt.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speed = (speedPrompt.getValue());
				System.out.println("Speed: " + speed);
			}
		}); 
		timeField.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				delay = timeField.getValue();
				System.out.println("Delay Time: "+ timeField.getValue());

			}
		});
		fire = new JButton("  Fire!  ");
		fire.setPreferredSize(new Dimension (100, 40));
		fire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start");
				timer.start();
			}
		});
		buttonReset = new JButton("Pause and Reset Position");
		buttonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				timer2.stop();
				patricktime = 0.005;
				time=0;
				i=0;
				X = 220;
				startx.setValue(0);
				Y= 400;
				starty.setValue(400);
				paint(g);
			}
		});

		button1 = new JButton("Firework 1");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 1;
				paint(g);
			}
		});
		button2 = new JButton("Firework 2");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 2;
				paint(g);
			}
		});
		button3 = new JButton("Firework 3");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 3;
				paint(g);
			}
		});
		button4 = new JButton("Firework 4");
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 4;
				paint(g);
			}
		});
		button6 = new JButton("Firework 6");
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 6;
				paint(g);
			}
		});
		button7 = new JButton("Firework 7");
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 7;
				paint(g);
			}
		});
		extraCreditSuperSpecialFirework = new JButton(" SUPER SPECIAL FIREWORK ");
		Font Font = new Font("A", 1, 13);
		extraCreditSuperSpecialFirework.setFont(Font);
		extraCreditSuperSpecialFirework.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.RED));
		extraCreditSuperSpecialFirework.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				firework = 5;
				for(int k = 0; k<5; k++) {
				System.out.println("ARE YOU READY TO GO SUPER SAYAN!?");
				}
				paint(g);
			}
		});

		westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		westPanel.add(button1);
		westPanel.add(button2);
		westPanel.add(button3);
		westPanel.add(button4);
		westPanel.add(extraCreditSuperSpecialFirework);
		westPanel.add(button6);
		westPanel.add(button7);
		westPanel.add(fire);
		westPanel.add(buttonReset);
	}
	protected class Paint extends JPanel {
	@Override
	public void paint(Graphics g) {

		super.paint(g);
		westPanel.setPreferredSize(new Dimension (210,200));
		g.setColor(Color.RED);
		timer2.setInitialDelay(50);
		if(time>delay) {
			explodeX= X;
			explodeY= Y;
			timer.stop();
			timer2.start();	}
		if(i>20) {
			timer.stop();
			timer2.stop();
			patricktime = 0.005;
			time=0;
			i=0;
			X = (startx.getValue())+210;
			Y= starty.getValue();
		}
		if(timer2.isRunning()) {

			switch(firework) { //Switch statement for firework explosion type
			case 1: 
				for(int j = 0; j<5; j++) {
					if(j==0 && i%4==0) {
						g.setColor(Color.BLUE);
					}
					else if(j==1 && i%3==0){
						g.setColor(Color.RED);
					}
					else if(j==2 && i%2 == 0){
						g.setColor(Color.YELLOW);
					}
					else if(j==3 && i%2==0){
						g.setColor(Color.MAGENTA);
					}
					else if(j==4 && i%2==0) {
						g.setColor(Color.PINK);
					}
					else {
						g.setColor(Color.GREEN);
					}
					g.drawOval((int) explodeX - 5 * (i + j) + 1, (int) explodeY - 5 * (i + j), 10 * (i + j), 10 * (i + j));
				}
				break;


			case 2: {
				g.setColor(Color.CYAN);
				if(i>10) {
					g.setColor(Color.YELLOW);
				}
				int step = 5;
				int f = -5;
				if(i<10) {
					if(i%2!=0) {
						do {
							g.drawLine((int)X, (int)Y,(int) X+40, (int)Y+step*(f));
							g.drawLine((int)X, (int)Y,(int) X-40, (int)Y+step*(f));
							g.drawLine((int)X, (int)Y,(int) X+step*(i), (int)Y+40);
							g.drawLine((int)X, (int)Y,(int) X+step*(i), (int)Y-40);
							f++;
						}
						while(f<6);
					}
				}
			}
			break;

			case 3: {
				g.setColor(Color.MAGENTA);
				if(i>9) {
					g.setColor(Color.BLUE);
				}
				int step = 5;
				int i = -5;
				do {
					g.drawLine((int)X, (int)Y,(int) X+step*(i), (int)Y+40);
					g.drawLine((int)X, (int)Y,(int) X+step*(i), (int)Y-40);
					i++;
				}
				while(i<6);
			}
			break;
			case 4: {
				g.setColor(Color.GREEN);
				if(i%2==0) {
					g.setColor(Color.RED);
				}
				else if(i%3==0) {
					g.setColor(Color.BLUE);
				}
				int step = 5;
				int adjust = 10+i*3;
				int f = 0;
				do {
					g.drawLine((int)X-adjust, (int)Y-adjust+step*f, (int)X-adjust+step*(f+1),(int) Y+adjust);
					g.drawLine((int)X-adjust+step*f, (int)Y+adjust,(int) X+adjust,(int) Y+adjust-step*(f+1));
					g.drawLine((int)X+adjust, (int)Y+adjust-step*f,(int) X+adjust-step*(f+1), (int)Y-adjust);
					g.drawLine((int)X+adjust-step*f,(int) Y-adjust, (int)X-adjust, (int)Y-adjust+step*(f+1));
					f++;
				}
				while(step*f<20);
			}
			break;
			case 5:{
				double newX=X;
				double newY=Y;
				System.out.println(explosionComponentX + explosionComponentY);
				if(i<4) {
				for(double p = 1; p <6; p++) {
				newX=((X)+60*explosionComponentX*p/2.5);
				newY=(Y+60*explosionComponentY*2/p);
				g.drawLine((int)X, (int)Y, (int)newX, (int)newY);
				}
				}
				else if(i<7) {
					for(int p = 1; p <6; p++) {
					g.fillOval((int)((X)+1.5+60*explosionComponentX*p/2.5), (int)(Y+1.5+60*explosionComponentY*2/p), 4, 4);
				}
				}
				else if(i<11) {
					for(int p = 1; p<6; p++) {
						for(int j = 1; j<5; j++) {
						g.drawLine(
						(int)((X)+1.5+60*explosionComponentX*p/2.5), 
						(int)(Y+1.5+60*explosionComponentY*2/p), 
						(int)((X)+1.5+60*explosionComponentX*p/2.5+10*explosionComponentX*j/.5), 
						(int)(Y+1.5+60*explosionComponentY*2/p+10*explosionComponentY*20/j));
					}
					}
				}
				else if(i<16) {
					for(int p = 1; p<6; p++) {
						for(int j = 1; j<5; j++) {
							for(int k = 0; k<5; k++) {
								if(k==0 && i%4==0) {
									g.setColor(Color.BLUE);
								}
								else if(k==1 && i%3==0){
									g.setColor(Color.RED);
								}
								else if(k==2 && i%2 == 0){
									g.setColor(Color.YELLOW);
								}
								else if(k==3 && i%2==0){
									g.setColor(Color.MAGENTA);
								}
								else if(k==4 && i%2==0) {
									g.setColor(Color.PINK);
								}
								else {
									g.setColor(Color.GREEN);
								}

							g.drawOval(
								(int)((X)+1.5+60*explosionComponentX*p/2.5+10*explosionComponentX*j/.5) - 5 * (k + (i-11))+1,
								(int)(Y+1.5+60*explosionComponentY*2/p+10*explosionComponentY*20/j)- 5 * (k + (i-11)),
								10 * (k + (i-11)), 10 * (k + (i-11)));
					}
						}
				}
			}
			}
			break;
			case 6:{
				for(int l =0; l<30; l++) {
					if(l%2==0) { g.setColor(Color.GREEN); }
					else { g.setColor(Color.BLUE); }
					g.drawArc((int)X,(int) Y, 30, 40*l/10, 40*l, 40*i);
				}
			}
			break;
			case 7:{
				double x1 = 0;
				double y1 = 80;
				double x2 = 20;
				double y2 = 20;
				double x3 = 20;
				double y3 = 20;
				double x11 = 0;
				double y11 = 80;
				double x22 = 20;
				double y22 = 20;
				double x33 = 20;
				double y33 = 20;
				for(double q = 0; q<Math.PI*4; q += Math.PI/20) {
					g.drawLine((int)(X+x1), (int)(Y+y1),(int)(X+ x2), (int)(Y+y2));
					g.drawLine((int)(X+x2), (int)(Y+y2), (int)(X+ x3), (int)(Y+y3));
					g.drawLine((int)(X+x3), (int)(Y+y3), (int)(X+x1), (int)(Y+y1));
					x1 = x11*Math.cos(q) + y11*Math.sin(q);
					x2 = x22*Math.cos(q) + y22*Math.sin(q);
					x3 = x33*Math.cos(q) + y33*Math.sin(q);
					y1 = -x11*Math.sin(q) + y11*Math.cos(q);
					y2 = -x22*Math.sin(q) + y22*Math.cos(q);
					y3 = -x33*Math.sin(q) + y33*Math.cos(q);
				}
			}
			break;	
			}
		}
		else {
			g.fillOval((int) X, (int) Y, 8, 8);
		}

	}
	}

	double patricktime=.007;
	protected class TimerCallback1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("this");
			patricktime+=.007;
			Xtraject = X; //used for extra credit trajectory calculations
			Ytraject = Y;
			X += .1*speed * Math.cos(Math.toRadians(launchAngle));
			Y = (Y + GRAVITY * patricktime - speed * .1*Math.sin(Math.toRadians(launchAngle)));
					explosionComponentY = ((Y-Ytraject)/(Math.pow((X-Xtraject)*(X-Xtraject)+(Y-Ytraject)*(Y-Ytraject), .5)));
					explosionComponentX = (X-Xtraject)/(Math.pow((X-Xtraject)*(X-Xtraject)+(Y-Ytraject)*(Y-Ytraject), .5));

			time=time+ 27.0/1000;
			System.out.println("TotalTime: " + (new DecimalFormat("#.###").format(time)));
			paint(g);

		}
	}

	protected class TimerCallback2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			paint(g);
			i++;
		}
	}

}