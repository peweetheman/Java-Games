//Patrick Phillips

import java.util.Scanner;

public class HW01 {

	public static void isAnagram(String string1, String string2) {
		char[] str1 = string1.toCharArray();
		char[] str2 = string2.toCharArray();
		boolean check = true;
		for(int i = 0; i<str1.length; i++) {
			if (str1[i] == str2[str1.length - 1 - i] ) {
			}
			else {
				check = false;
			}
		}
		if(check) {
			System.out.println("These two are anagrams");
		}
		else {
			System.out.println("These two aren't anagrams");
		}

	}
	
	public static void isRotation(String string1, String string2) {
		if (string1.length() == string2.length()) { 
		string1 = string1 + string1;
		if(string1.contains(string2)) {
			System.out.println("One string is a rotation of the other");
		}
		else {
			System.out.println("One string is not a rotation of the other");
		}
		}
		else {
			System.out.println("One string is not a rotation of the other");
		}
		
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the first string: ");
		String string1 = scan.nextLine();
		System.out.print("Enter the second string: ");
		String string2 = scan.nextLine();
		isAnagram(string1, string2);
		System.out.print("Enter the first string: ");
		String string3 = scan.nextLine();
		System.out.print("Enter the second string: ");
		String string4 = scan.nextLine();
		isRotation(string3, string4);
	}

}
