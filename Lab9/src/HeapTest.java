
public class HeapTest {

	public static void main(String[] args) {
		Heap heap = new Heap();
		int[] arr = {5, 18, 3, 25, 27, 45, 97, 88, 26, 16, 49, 67};
		heap.heapify(arr);
		for(int i = 0; i< arr.length; i ++) {
			System.out.print(arr[i] + " ");
		}
		
		System.out.println();
		int[] arr2 = {15, 99, 3, 77, 27, 45, 7, 88, 26, 5};
		heap.heapsort(arr);
		for(int i = 0; i< arr.length; i ++) {
			System.out.print(arr[i] + " ");
		}
	}

}
