//Patrick Phillips
import java.util.ArrayList;
import java.util.List;

public class Lab3Task2 {

	public static void print2Darray(int[][] input) { //from task 1
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
	public static void print2DarrayList(List<ArrayList<Integer>> input) { //from task 1
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

	public static void runningSum2DArray(int[][] input, int direction) { //1 is left, 2 is right, 3 is up, 4 is down
		int runningSum = 0;
		if(direction == 1) {
			for(int i =0; i<input.length; i++) {
				for(int j = input[i].length-1; j>=0; j--) {
					runningSum = runningSum + input[i][j];
					input[i][j] = runningSum;
				}
				runningSum = 0;
			}
			print2Darray(input);
		}
		else if(direction == 2) {
			for(int i =0; i<input.length; i++) {
				for(int j = 0; j<input[i].length; j++) {
					runningSum = runningSum + input[i][j];
					input[i][j] = runningSum;
				}
				runningSum = 0;
			}
			print2Darray(input);
		}
		else if(direction == 3) {
			for(int j = 0; j<input.length;j++) { 		
				for(int i =input.length-1; i>=0; i--) {
					runningSum = runningSum + input[i][j];
					input[i][j] = runningSum;
				}
				runningSum = 0;
			}
			print2Darray(input);
		}
		else if(direction == 4) {
			for(int j = 0; j<input.length; j++) {
				for(int i =0; i<input[j].length; i++) {
					runningSum = runningSum + input[i][j];
					input[i][j] = runningSum;
				}
				runningSum = 0;
			}
			print2Darray(input);
		}
	}

	public static void runningSum2DArrayList(List<ArrayList<Integer>> input, int direction) { //1 is left, 2 is right, 3 is up, 4 is down
		int runningSum = 0;
		if(direction == 1) {
			for(int i =0; i<input.size(); i++) {
				for(int j = input.size()-1; j>=0; j--) {
					runningSum = runningSum + input.get(i).get(j);
					input.get(i).set(j, runningSum);
				}
				runningSum = 0;
			}
			print2DarrayList(input);
		}
		else if(direction == 2) {
			for(int i =0; i<input.size(); i++) {
				for(int j = 0; j<input.size(); j++) {
					runningSum = runningSum + input.get(i).get(j);
					input.get(i).set(j, runningSum);
				}
				runningSum = 0;
			}
			print2DarrayList(input);
		}
		else if(direction == 3) {
			for(int j = 0; j<input.size();j++) { 		
				for(int i =input.size()-1; i>=0; i--) {
					runningSum = runningSum + input.get(i).get(j);
					input.get(i).set(j, runningSum);
				}
				runningSum = 0;
			}
			print2DarrayList(input);
		}
		else if(direction == 4) {
			for(int j = 0; j<input.size(); j++) {
				for(int i =0; i<input.size(); i++) {
					runningSum = runningSum + input.get(i).get(j);
					input.get(i).set(j, runningSum);
				}
				runningSum = 0;
			}
			print2DarrayList(input);
		}
	}

	public static void main(String[] args) {

		int[][] array = {{10, 15, 30, 40},{15, 5, 8, 2}, {20, 2, 4, 2},{1, 4, 5, 0}};
		System.out.println("Up Direction:");
		runningSum2DArray(array, 3);
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
		System.out.println("USING ARRAYLIST OF ARRAYLISTS AND TOP TO BOTTOM SUMS: ");
		runningSum2DArrayList(arraylist, 4);


	}

}