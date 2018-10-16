/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

public class Sorting {


	/**
	 * 
	 * Sorts the numbers present in the file based on the algorithm provided.
	 * 0 = Arrays.sort() (Java Default)
	 * 1 = Bubble Sort
	 * 2 = Selection Sort 
	 * 3 = Insertion Sort 
	 * 4 = Mergesort
	 * 5 = Quicksort
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args)  { 
		In ina = new In(args[0]);
		In inb = new In(args[0]);
		In inc = new In(args[0]);
		In ind = new In(args[0]);
		// Storing file input in an array

		// TODO: Generate 3 other arrays, b, c, d where
		// b contains sorted integers from a (You can use Java Arrays.sort() method)
		// c contains all integers stored in reverse order 
		// (you can have your own O(n) solution to get c from b
		// d contains almost sorted array 
		//(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.   
		int[] a = ina.readAllInts();
		int[] b = inb.readAllInts();
		int[] c = inc.readAllInts();
		int[] d = ind.readAllInts();
		Arrays.sort(b);
		c = Arrays.copyOf(b, b.length);

		for(int i = 0; i < c.length/2; i++) {
			int temp = c[i];
			c[i] = c[c.length - i - 1];
			c[c.length - i - 1] = temp;
		}
		d = Arrays.copyOf(b, b.length);
		int swaps = (int) (0.1 * d.length);
		for(int i = 0; i < swaps; i ++) {
			int random1 = (int) (Math.random()*d.length);
			int random2 = (int) (Math.random()*d.length);
			while(random1 == random2) {
				random2 = (int) (Math.random()*d.length);
			}
			int temp = d[random1];
			d[random1] = d[random2];
			d[random2] = temp;
		}




		//TODO: 
		// Read the second argument and based on input select the sorting algorithm
		//  * 0 = Arrays.sort() (Java Default)
		//  * 1 = Bubble Sort
		//  * 2 = Selection Sort 
		//  * 3 = Insertion Sort 
		//  * 4 = Mergesort
		//  * 5 = Quicksort
		//  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those. 
		// For runtime and priting, you can use the same code from Lab 4 as follows:

		// TODO: For each array, a, b, c, d:
	//	Stopwatch timer = new Stopwatch();
		int n = Integer.parseInt(args[1]);
		int[] result;
		for(int i = 0; i < 4; i ++) {
			Stopwatch timer = new Stopwatch();
			// TODO: Perform Sorting and store the result in an  array


			String algorithmUsed = "";
			String arrayUsed = "";

			if(i == 0) {
				result = sort(a, n);
				arrayUsed = "a";
			}else if(i == 1) {
				result = sort(b, n);
				arrayUsed = "b";
			}else if(i == 2) {
				result = sort(c, n);
				arrayUsed = "c";
			}else {
				result = sort(d, n);
				arrayUsed = "d";
			}
			if(n == 0) {
				algorithmUsed = "Arrays.sort()";
			}
			if(n == 1) {
				algorithmUsed = "Bubble Sort";
			}
			if(n == 2) {
				algorithmUsed = "Selection Sort";
			}
			if(n == 3) {
				algorithmUsed = "Insertion Sort";
			}
			if(n == 4) {
				algorithmUsed = "Mergesort";
			}
			if(n == 5) {
				algorithmUsed = "Quicksort";
			}

			double time = timer.elapsedTimeMillis();

			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			//TODO: Replace with your own netid
			String netID = "pphill10";
			//TODO: Replace with the algorithm used 

			//TODO: Replace with the  array used 

			StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
			// Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
			
			try
			{
			    PrintWriter pr = new PrintWriter(arrayUsed + ".txt");
			    for(int x = 0; x < result.length; x++) {
			    	pr.println((result[x]));
			    	
			    }
			    pr.close();
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			    System.out.println("That file doesn't exist!");
			}
		}
		

	} 
	//Sorts copied from Biswas slides
	public static int[] sort(int[] a, int n) {
		if(n == 0) { //Arrays.sort()
			Arrays.sort(a);
			return a;
		}
		if(n == 1) { //Bubble Sort
			int left, right, size = a.length;
			for(left = size - 1; left > 0; left--) {
				for(right = 0; right < left; right ++) {
					if(a[right] > a[right + 1]) {
						int temp = a[right];
						a[right] = a[right + 1];
						a[right + 1] = temp;
					}
				}
			}
			return a;
		}
		if(n == 2) { //Selection Sort
			int size = a.length;
			for (int i = 0; i < size-1; i++) {
				int index = i;
				for(int j = i + 1; j < size; j ++) {
					if (a[j] < a[index]) {
						index = j;
					}
				}
				int temp = a[index];
				a[index] = a[i];
				a[i] = temp;
			}
			return a;
		}
		if(n == 3) { //Insertion Sort
			int temp, j, size = a.length;
			for (int i = 1; i < size; i++) {
				temp = a[i];
				j = i - 1;
				while (j >= 0 && a[j] > temp) {
					a[j + 1] = a[j];
					j--;
				}
				a[j + 1] = temp;
			}
			return a;
		}
		if(n == 4) { //Mergesort
			mergeSort(a);
			return a;
		}
		if(n == 5) { //Quicksort
			recursiveQuickSort(a, 0, a.length- 1);
			return a;
		}
		return a;
	}
	public static void recursiveQuickSort(int[] arr, int left, int right)
	{
		if (right <= left) return;
		// pick a random pivot
		int m = (int)(Math.random() * (right-left+1));
		int temp = arr[right];
		arr[right] = arr[left + m];
		arr[left + m] = temp;
		//		swap(arr, right, left+m);
		int i=left-1, j=right;
		while (true) {
			while (arr[++i] <= arr[right])
				if (i == right) break;
			while (arr[--j] >= arr[right])
				if (j == left || j == i) break;
			if (j <= i) break;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			//			Sort.swap(arr, i, j);
		}
		if (i < right)
		{
			temp = arr[i];
			arr[i] = arr[right];
			arr[right] = temp;
			//			Sort.swap(arr, i, right);
		}
		// recursively sort the left & the right parts
		recursiveQuickSort(arr, left, i-1);
		recursiveQuickSort(arr, i+1, right);
	}

	public static void mergeSort(int [] arr) {
		if (arr.length <= 1) return;
		int[] left = new int[arr.length/2];
		int [] right = new int[arr.length - arr.length/2];
		System.arraycopy(arr, 0, left, 0, arr.length/2);
		System.arraycopy(arr, arr.length/2, right, 0, arr.length - arr.length/2);
		mergeSort(left);
		mergeSort(right);
		merge(arr, left, right);
	}

	public static void merge(int[] target, int[] left, int[] right) {
		int i=0, j=0, k=0;
		while (i < left.length && j < right.length) {
			if (left[i] < right[j])
				target[k++] = left[i++];
			else
				target[k++] = right[j++];
		}
		while (i < left.length) 
			target[k++] = left[i++];
		while (j < right.length) 
			target[k++] = right[j++];
	}
} 