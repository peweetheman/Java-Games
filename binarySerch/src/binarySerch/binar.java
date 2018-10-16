package binarySerch;

public class binar {

	public static int runBinarySearchRecursively(
			  int[] sortedArray, int key, int low, int high) {
			    int middle = (low + high) / 2;
			         
			    if (high < low) {
			        return -1;
			    }
			 
			    if (key == sortedArray[middle]) {
			        return middle;
			    } else if (key < sortedArray[middle]) {
			        return runBinarySearchRecursively(
			          sortedArray, key, low, middle - 1);
			    } else {
			        return runBinarySearchRecursively(
			          sortedArray, key, middle + 1, high);
			    }
			}
	public static void main(String[] args) {
		int[] arr = new int [1024];
		for(int i =0; i< 1023; i++) {
			arr[i] = i;
		}
		System.out.print(runBinarySearchRecursively(arr, 66, 0, 1023));
	}

}
