import java.util.Arrays;

public class Lab8P4 {

	public static int operations = 0;

	public static void main(String[] args) {
		int[] cookies = {5, 6 , 7, 7 ,7 ,8, 14, 22};
		System.out.println(Jesse_cookies(cookies, 18));
	}
	public static int Jesse_cookies(int[] cookies, int k) {
		Arrays.sort(cookies);
		if(cookies[0] > k) {
			return operations;
		}
		int[] newCookies = new int[cookies.length-1];
		try {
			newCookies[0] = cookies[0] + 2*cookies[1];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return -1;
		}
		for(int i=1; i<cookies.length-1; i++) {
			newCookies[i] = cookies[i+1];
		}
		operations++;

		return Jesse_cookies(newCookies, k);
	}

}
