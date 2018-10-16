
public class Heap {

	public void heapify(int[] arr) {
		for (int i=arr.length/2; i>=0; i--)
			recursiveSink(arr, i, arr.length);
	}

	public void heapsort(int[] arr) {
		heapify(arr);
		for (int i=arr.length-1; i>=0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			recursiveSink(arr, 0, i);
		}
	}

	public void recursiveSink(int[] arr , int i, int n) {
		int leftChild = 2*i + 1;
		if (n > arr.length || leftChild >= n) {
			return;
		}
		int rightChild = leftChild + 1;
		int current;
		if(rightChild>=n) {
			current = leftChild;
		}else if(arr[rightChild] > arr[leftChild]) {
			current = rightChild;
		}
		else {
			current = leftChild;
		}
		if (arr[i] < arr[current]) {
			int temp = arr[i];
			arr[i] = arr[current];
			arr[current] = temp;
			recursiveSink(arr, current, n);
		}
	}
}
