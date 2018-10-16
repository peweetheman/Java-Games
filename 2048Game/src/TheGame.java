import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TheGame extends JFrame implements KeyListener{
	private int[][] board;
	private JButton[][] labels;
	private int count = 0;
	private int direction;
	private boolean validMove;
	private boolean empty;

	public TheGame() { //Constructor
		addKeyListener(this);
		setTitle("2048 Project");
		GridLayout layout = new GridLayout(4, 4);
		setLayout(layout);
		layout.setHgap(5);
		layout.setVgap(5);
		setVisible(true);
		setSize(550, 500);
		Font font = new Font("Font", 25, 24);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new int[4][4];
		labels = new JButton[4][4];
		generate(board);
		generate(board);
		for (int i = 0; i < labels.length; i++) { //Adding array of JButtons to the grid
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JButton();
				labels[i][j].setFont(font);
				labels[i][j].setText(Integer.toString(board[i][j]));
				labels[i][j].setSize(50, 50);
				labels[i][j].setEnabled(false);
				add(labels[i][j]).setLocation(4, 4);
			}
		}
		System.out.println("Initial Board:");
		print2Darray(board);
		updateBoard();
	}
	public static void print2Darray(int[][] input) { //From Lab
		for(int i =0; i<input.length; i++) {
			for(int j = 0; j<input[i].length; j++) {
				System.out.print(input[i][j] + " ");
				if(input[i][j]<1000) {
					System.out.print(" ");
				}
				if(input[i][j]<100) {
					System.out.print(" ");
				}
				if(input[i][j]<10) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) { //Takes input key and then sets direction and calls move() function
		System.out.println("");
		int keyCode = e.getKeyCode();
		if ((keyCode ==  KeyEvent.VK_W) ||(keyCode ==  KeyEvent.VK_UP)) {
			System.out.println("Key Pressed: W");
			direction = 3;
		}
		else if ((keyCode ==  KeyEvent.VK_A) ||(keyCode ==  KeyEvent.VK_LEFT)) {			
			System.out.println("Key Pressed: A");
			direction = 2;
		}
		else if ((keyCode ==  KeyEvent.VK_S) ||(keyCode ==  KeyEvent.VK_DOWN)) {			
			System.out.println("Key Pressed: S");
			direction = 4;
		}
		else if ((keyCode ==  KeyEvent.VK_D) ||(keyCode ==  KeyEvent.VK_RIGHT)) {			
			System.out.println("Key Pressed: D");
			direction = 1;
		}
		else if (keyCode == KeyEvent.VK_R) {
			System.out.println("Key Pressed: R");
			board = new int[4][4];
			count = 0;
			generate(board);
		}
		else if (keyCode == KeyEvent.VK_Q) {
			System.out.println("Key Pressed: Q");
			System.exit(1);
		}
		move(true);
		if(validMove) {
			generate(board);
			count++;
		}
		boolean lose = true;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					try{
						if ((board[i][j] == board[i][j+1]) || (board[i][j] == board[i+1][j])) { //Checking to see if user has any valid moves left
							lose = false;
						}
					}catch(IndexOutOfBoundsException p) {
							}
						}
					}
		if(lose && empty) {
			JOptionPane.showMessageDialog(this, "YOU LOSE" + "\n" + "Valid Moves: " + count);
		}
		else if(!validMove) {
			System.out.println("INVALID MOVE");
		}
		else {
		System.out.println("Valid Moves Made: " + count);
		System.out.println("Maximum value: " + getMaximum(board));
		print2Darray(board);
		updateBoard();
		}
	}
	public void generate(int[][] board) { //generates 2 or 4 in random location
		int randomI = (int)(Math.random()*4);
		int randomJ = (int)(Math.random()*4);
		int value = (int)(Math.random() * 10 + 1);
		if(value >8) {
			value = 4;
		}
		else {
			value = 2;
		}
		
		if (board[randomI][randomJ] == 0) {
			board[randomI][randomJ] = value;
		}
		else {
			generate(board);
		}
	}

	public int getMaximum(int[][] board) {
		int max = board[0][0];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] > max) {
					max = board[i][j];
					if(max>2000) {
						JOptionPane.showMessageDialog(this, "YOU WIN!");
					}
				}
			}
		}
		return max;
	}
	public void move(boolean check) { //main movement function, uses temporary array to keep track of moves
		int num = 0;
		int temp[][] = new int[4][4];
		int test[][] = new int[4][4];
		for(int i = 0; i<board.length; i++) {
			for(int j = 3; j>=0; j--) {
				test[i][j] = board[i][j];
			}
		}
		if(direction == 1) {
			num = 3;
			for(int row = 0; row<board.length; row++) {
				for(int j = 3; j>=0; j--) {
					if(board[row][j] != 0) {
						temp[row][num] = board[row][j];
						num--;
					}
				}
				num = 3;
			}
		}
		else if(direction == 2) {
			for(int row = 0; row<board.length; row++) {
				for(int j = 0; j<board.length; j++) {
					if(board[row][j] != 0) {
						temp[row][num] = board[row][j];
						num++;
					}
				}
				num = 0;
			}
		}
		else if(direction == 3) {
			for(int column = 0; column<board.length; column++) {
				for(int i = 0; i<board.length; i++) {
					if(board[i][column] != 0) {
						temp[num][column] = board[i][column];
						num++;
					}
				}
				num = 0;
			}
		}
		else if(direction == 4) {
			num = 3;
			for(int column = 0; column<board.length; column++) {
				for(int i = 3; i>=0; i--) {
					if(board[i][column] != 0) {
						temp[num][column] = board[i][column];
						num--;
					}
				}
				num = 3;
			}
		}
		validMove = false;
		empty = true;
		board = temp;
		for(int i = 0; i<board.length; i++) {
			for(int j = 3; j>=0; j--) {
				if(test[i][j] != board[i][j]) {
					validMove = true;
				}
			}
		}
		for (int i = 0; i <board.length; i++){
			for (int j = 0; j <board[i].length; j++){

				if (board[i][j] == 0){
					empty = false;
				}
			}
		}
		if(check) {
			checkAndMerge();
		}	
		if(!check) {
			validMove = true;
		}
		updateBoard();
	}

	public void checkAndMerge() { //merges in given direction if available
		if(direction ==1) {
			for(int row = 0; row<board.length; row++) {
				for(int j = 3; j>=0; j--) {
					if(j!=0) {
						if((board[row][j] != 0) && (board[row][j] == board[row][j-1])) {
							board[row][j]*=2;
							board[row][j-1] = 0;
							move(false);
						}
					}
				}
			}
		}
		if(direction ==2) {
			for(int row = 0; row<board.length; row++) {
				for(int j = 0; j<board.length; j++) {
					if(j!=3) {
						if((board[row][j] != 0) && (board[row][j] == board[row][j+1])) {
							board[row][j]*=2;
							board[row][j+1] = 0;
							move(false);
						}
					}
				}
			}
		}
		if(direction ==3) {
			for(int column = 0; column<board.length; column++) {
				for(int i = 0; i<board.length; i++) {
					if(i!=3) {
						if((board[i][column] != 0) && (board[i][column] == board[i+1][column])) {
							board[i][column]*=2;
							board[i+1][column] = 0;
							move(false);
						}
					}
				}
			}
		}
		if(direction ==4) {
			for(int column = 0; column<board.length; column++) {
				for(int i = 3; i>=0; i--) {
					if(i!=0) {
						if((board[i][column] != 0) && (board[i][column] == board[i-1][column])) {
							board[i][column]*=2;
							board[i-1][column] = 0;
							move(false);
						}
					}
				}
			}
		}
	}

	public void updateBoard() { //updates JButtons and sets colors
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				labels[i][j].setText(Integer.toString(board[i][j]));
				if(board[i][j] == 0) {
					labels[i][j].setText("");
					labels[i][j].setBackground(Color.gray);
				}
				if(board[i][j] == 2) {
					labels[i][j].setBackground(Color.YELLOW);
				}
				if(board[i][j] == 4) {
					labels[i][j].setBackground(Color.GREEN);
				}
				if(board[i][j] == 8) {
					labels[i][j].setBackground(Color.ORANGE);
				}
				if(board[i][j] == 16) {
					labels[i][j].setBackground(new Color(120, 120, 0));
				}
				if(board[i][j] == 32) {
					labels[i][j].setBackground(Color.CYAN);
				}
				if(board[i][j] == 64) {
					labels[i][j].setBackground(Color.RED);
				}
				if(board[i][j] == 128) {
					labels[i][j].setBackground(Color.LIGHT_GRAY);
				}
				if(board[i][j] == 256) {
					labels[i][j].setBackground(Color.blue);
				}
				if(board[i][j] == 512) {
					labels[i][j].setBackground(Color.PINK);
				}
				if(board[i][j] == 1024) {
					labels[i][j].setBackground(Color.GRAY);
				}
				if(board[i][j] == 2048) {
				}
			}
		}
	}
	public static void main(String[] args) {
		TheGame game = new TheGame();
		game.setVisible(true);	
	}

}
