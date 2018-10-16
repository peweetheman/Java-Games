
public class EightQueens {

	public static boolean findEightQueen(boolean[][] board, int colum){
		if(colum>=8) {
			printQueens(board);
			return true;
		}

		for(int row = 0; row<8; row++) {
			if(validMove(board, row, colum)) {
				board[row][colum] = true;
				if(!findEightQueen(board, colum + 1)) {
					board[row][colum] = false;
				}	
				else{
					return true;
				}
			}
		}
		return false;
	}

	//Base Case print:
	public static void printQueens(boolean[][] board) {
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				if (board[i][j]) {
					System.out.print(" Q ");
				}
				else{
					System.out.print(" * ");
				}

			}
			System.out.println("");
		}
	}

	private static boolean validMove(boolean[][] board, int row, int colum) {
		for(int i = 0; i < colum; i++)
			if(board[row][i]) {
				return false;
			}
		int j = colum;
		for(int i = row; i>=0 && j>=0; i--, j--) {
			if(board[i][j]) {
				return false;
			}
		}
		j = colum;
		for(int i =row; i<8 && j>=0; i++, j--) {
			if(board[i][j]) {		
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		boolean[][] board = new boolean[8][8];
		findEightQueen(board, 0);
	}
}
