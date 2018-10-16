//Patrick Phillips
import java.util.*;

public class Lab3Task3 {

	// Using basic while / for loop
	public static void printArrayListBasicLoop(ArrayList<Integer> al) {
		for(int i = 0; i<al.size(); i++) {
			int number = al.get(i);
			System.out.print(number + " ");
		}
	}
	// Using enhanced for loop (:)
	public static void printArrayListEnhancedLoop(ArrayList<Integer> al) {
		for(int number : al) {
			System.out.print(number + " ");
		}
	}
	// Using basic for loop with iterator
	public static void printArrayListForLoopListIterator(ArrayList<Integer> al) {
		for(Iterator<Integer> iterator = al.iterator(); iterator.hasNext(); ) {
			int number = iterator.next();
			System.out.print(number + " ");
		}
	}
	// Using basic while loop with iterator
	public static void printArrayListWhileLoopListIterator(ArrayList<Integer> al) {
		Iterator<Integer> iterator = al.iterator();
		while(iterator.hasNext()) {
			int number = iterator.next();
			System.out.print(number + " ");
		}
	}
	// Using Java 8 forEach with lambda (use ArrayList.forEach method)
	public static void printArrayListLambda(ArrayList<Integer> al) {
		al.forEach(number -> System.out.print(number + " "));
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<>();
		array.add(23);
		array.add(42);
		printArrayListLambda(array);
	}

}
