import java.util.Scanner;

/*
Patrick Phillips
pphill10
Lab Section Tuesday-Thursday 9:40-10:55 Gavet 208
I did not collaborate with anyone on this assignment
 */

public class Problem01 {

	public static int multiply(int x, int y) {
		    if (y != 0 && x != 0) {
		        if (y > 0) {
		            return multiply(x, y - 1) + x;
		        } else {
		            return multiply(x, y + 1) - x;
		        }
		    }
		    return 0;
		}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
			System.out.print("Enter a number two numbers: ");
			int x = scan.nextInt();
			int y = scan.nextInt();
			System.out.println(multiply(x, y));
	}

}
