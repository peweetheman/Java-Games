//Patrick Phillips

import java.util.*;

public class Lab3Task1 {

	public static void print2Darray(int[][] input) {
		for(int i =0; i<input.length; i++) {
			for(int j = 0; j<input[i].length; j++) {
				System.out.print(input[i][j] + " ");
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
	public static void print2DarrayList(List<ArrayList<Integer>> input) {
		for(int i =0; i<input.size(); i++) {
			for(int j = 0; j<input.get(0).size(); j++) {
				System.out.print(input.get(i).get(j)+ " ");
				if(input.get(i).get(j)<100) {
					System.out.print(" ");
				}
				if(input.get(i).get(j)<10) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {

		int array[][] = {{10, 15, 30, 40},{15, 5, 8, 2}, {20, 2, 4, 2},{1, 4, 5, 0}};
		print2Darray(array);
		List<ArrayList<Integer>> arraylist = new ArrayList<ArrayList<Integer > >(); 
		for(int i = 0; i<4; i++) {
			arraylist.add(new ArrayList<Integer>());
		}
		arraylist.get(0).add(10);
		arraylist.get(0).add(15);
		arraylist.get(0).add(30);
		arraylist.get(0).add(40);
		arraylist.get(1).add(15);
		arraylist.get(1).add(5);
		arraylist.get(1).add(8);
		arraylist.get(1).add(2);
		arraylist.get(2).add(20);
		arraylist.get(2).add(2);
		arraylist.get(2).add(4);
		arraylist.get(2).add(2);
		arraylist.get(3).add(1);
		arraylist.get(3).add(4);
		arraylist.get(3).add(5);
		arraylist.get(3).add(0);
		System.out.println("USING ARRAYLIST OF ARRAYLISTS: ");
		print2DarrayList(arraylist);

		
	}

}
