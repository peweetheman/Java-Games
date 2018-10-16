import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * This program creates a firework on each mouse click.  After a fixed number
 * of mouse clicks, a finale containing many fireworks is displayed.
 * 
 * @author Barbara Lerner 
 */

public class FireworkShow extends JComponent implements MouseListener
{
	/* Width of the frame */
	private static final int FRAME_WIDTH = 300;

	/* Height of the frame */
	private static final int FRAME_HEIGHT = 350;

   // The number of regular fireworks
    private static final int MAX_FIREWORKS = 10;
    
    // The number of fireworks in the finale
    private static final int NUM_IN_FINALE = 25;
    
    // How many fireworks have been produced so far
    private int numFireworks = 0;
    
    // Used to generate a random color for the firework.  The red & green components are
    // bright while the blue component is dark.  This is done to make it more likely that
    // the color will stand out against the sky background.  The random number generated
    // is also used to produce a random size.
    private Random randomGen= new Random();
    private static final int BRIGHT_LOW_VALUE = 100;
    private static final int BRIGHT_HIGH_VALUE = 255;
    private static final int DARK_LOW_VALUE = 0;
    private static final int DARK_HIGH_VALUE = 150;
    private static final int MIN_SIZE = 25;
    private static final int MAX_SIZE = 75;
    
    // Remembers if the finale has occurred.  We only want there to be 1 finale.
    private boolean finaleStarted = false;
    
    // The size of the window currently.  This could change if the user resizes
    // the window.
    private int width;
    private int height;
    
    // The last regular firework created
    private fere regularFirework;
    
    // The fireworks that are in the finale
    private fere[] finaleFireworks = new fere[NUM_IN_FINALE];

    // The number of finale fireworks that have been created so far.
	private int finaleCount = 0;
    
    /**
     * Draw the sky along with the fireworks currently visible
     * @param g the graphics object to draw on
     */
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	// Draw the sky
    	Dimension d = getSize();
    	width = d.width;
    	height = d.height;
    	g.setColor(new Color (24, 5, 162));
        g.fillRect (0, 0, width, height);
        
        // If a regular firework is visible, draw it.
        if (regularFirework != null) {
        	regularFirework.paint(g);
        }
        
        // Draw all the finale fireworks that have been created.
        for (int i = 0; i < finaleCount; i++) {
        	finaleFireworks[i].paint(g);
        }
        
    }       
    
    /**
     * Creates an individual firework of a random color and random size.
     * @param point the center of the firework
     * @return the firework created
     */
    private fere shootOneFirework(Point2D point) {
    	// Pick a random color
    	int redValue = randomGen.nextInt(BRIGHT_HIGH_VALUE - BRIGHT_LOW_VALUE) + BRIGHT_LOW_VALUE;
    	int greenValue = randomGen.nextInt(BRIGHT_HIGH_VALUE - BRIGHT_LOW_VALUE) + BRIGHT_LOW_VALUE;
    	int blueValue = randomGen.nextInt(DARK_HIGH_VALUE - DARK_LOW_VALUE) + DARK_LOW_VALUE;
        Color nextColor = new Color (redValue, greenValue, blueValue);
        
        // Pick a random size
        int size = randomGen.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
        
        // Create the firework
        return new fere (point, nextColor, size);
    }
    
    /**
     * Create the fireworks for the finale.
     */
    private void finale() {
        // Set finaleStarted to true so that later mouse clicks will not display another
        // finale.
        finaleStarted = true;
        regularFirework = null;
        
        while (finaleCount < NUM_IN_FINALE) {
            // Create the next firework at a random location.
            finaleFireworks[finaleCount] = shootOneFirework(new Point2D.Double (randomGen.nextInt(width), randomGen.nextInt(height)));
            
            // Increase the counter so that the while loop will eventually end.
            finaleCount++;
        }
    }

    /**
     * On each mouse click create a firework where the user clicks until
     * all the regular fireworks are used up.  The next click creates the finale.
     * After that mouse clicks do nothing.
     * @param point where the user clicked.
     */
	public void mousePressed(MouseEvent e) {
        // If there are more regular fireworks, shoot a regular firework 
        // where the user clicked.
        if (numFireworks < MAX_FIREWORKS) {
            regularFirework = shootOneFirework(e.getPoint());
            numFireworks++;
        }
        
        // If we have run out of regular fireworks and the finale has not
        // happened yet, do the finale.
        else if (!finaleStarted) {
            finale();
        }
        
        // Draw the sky with the new firework(s)
        repaint();
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// Create the window and set its size.
		JFrame f = new JFrame();
		f.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

		// Exit the application when the user closes the frame.
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the panel that will display the image and text
		FireworkShow sky = new FireworkShow();
		sky.addMouseListener(sky);

		// Add the venus panel to the center of the window
		Container contentPane = f.getContentPane();
		contentPane.add(sky, BorderLayout.CENTER);

		// Display the window.
		f.setVisible(true);
	}
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Draws a pretty firework on the display.
 * 
 * @author Barbara Lerner
 */
public class fere 
{
    // The radius we want the firework to have
    private int fireworkRadius;
    
    // The color the firework should have.
    private Color fireworkColor;
    
    // Where the center of the firework should be.
    private double horizontalOffset;
    private double verticalOffset;
    
    /**
     * Creates and displays a firework
     * @param point the center of the firework
     * @param color the color the firework should be
     * @param radius the size of the firework
     * @param canvas the canvas to draw on
     */
    public fere(Point2D center, Color color, int radius)
    {
        fireworkRadius = radius;
        fireworkColor = color;
        horizontalOffset = center.getX();
        verticalOffset = center.getY();
    }
    
    /**
     * Draw a firework
     * @param g the graphics object to draw on
     */
    public void paint(Graphics g) {
    	// As of Java 1.2 (the current verion is 1.6), the object passed in for this parameter
    	// has the type Graphics2D.  To take advantage of the additional drawing capabilities,
    	// we need to cast the object into a Graphics2D object.
    	Graphics2D g2D = (Graphics2D) g;
    	
    	// Tells the graphics object which screen coordinate to treat as the origin.
    	g2D.translate(horizontalOffset, verticalOffset);
    	
    	
    	g2D.setColor(fireworkColor);
    	
        // angle is the angle of the line from the center.  The first line
        // is horizontal
        double angle = 0;

        // Repeat until we have swept through a circle, which is 2 * PI radians.
        while (angle < 2 * Math.PI) {
        	// This tells the graphics object how far to rotate the object.
        	g2D.rotate(angle);
        	
            // Draw a line.  Note that we always draw a horizontal line and we always use
        	// (0, 0) as the origing.  The translate and rotate calls reposition the
        	// origin and do the rotation.
        	g2D.drawLine(-fireworkRadius, 0, fireworkRadius, 0);
                      
            // Increase the angle so the next line will be angled differently
            // and also so the loop will eventually exit.
            angle = angle + Math.PI / 8;
        }
    }
}