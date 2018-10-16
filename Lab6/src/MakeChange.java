import java.util.ArrayList;

public class MakeChange {

	public static int[] CoinValues = {25, 10, 5, 1};
	public static int makeChange(int n) {
		
		if(n<=0) {
			return 0;
		}
		return makeChanges(n, 0);
	}
	public static int makeChanges(int n, int index) {
		if ((n==0) || index == 3) {
			return 1;
		}
		if(n<0) {
			return 0;
		}
		int ways = 0;
		for(int i = index; i<CoinValues.length; i++) {
			ways += makeChanges(n-CoinValues[i], i);
		}
		return ways;
	}
	public static void main(String[] args) {

		System.out.println("ways to make 50 cents: "  + makeChange(50));
		System.out.println("ways to make 30 cents: "  + makeChange(100));

	}

}
