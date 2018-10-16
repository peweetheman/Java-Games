//Patrick Phillips
//pphill10
import java.util.function.Function;

public class HW02 {

	/*
	 public static void printArray(Object[] input) {
		    for (Object element : input)
		      System.out.printf("%s ", element);
		    System.out.println();
		  }
	public static void printArray(Integer[] input) {
		for (int element : input)
		      System.out.printf("%s ", element);
		    System.out.println();
		  }
	public static void printArray(String[] input) {
		for (String element : input)
		      System.out.printf("%s ", element);
		    System.out.println();
		  }
	public static void printArray(Double[] input) {
		for (double element : input)
		      System.out.printf("%s ", element);
		    System.out.println();
		  }
	public static void printArray(Character[] input) {
		for (char element : input)
		      System.out.printf("%s ", element);
		    System.out.println();
		  }
	*/
	 public static <E> void printArray(E[] input) {
		    for (E element : input)
		      System.out.printf("%s ", element);
		    System.out.println();
		  }
	 /*
	// Comparable is a raw type. References to generic type Comparable<T> should be parameterized
	// Type safety: The method compareTo(Object) belongs to the raw type Comparable. References to generic type Comparable<T> should be parameterized
	 public static Comparable getMax(Comparable[] input) {
			Comparable max = input[0];
			 for (Comparable element : input) {
			     if((element.compareTo(max))>0) {
				 max = element;
			     }
			 }
			 return max;
		 }
	  */
	 public static <E extends Comparable<E>> E getMax(E[] input) {
			E max = input[0];
			for (int i = 1; i < input.length; i++) {
	            if ((input[i].compareTo(max))>0){
	                max = input[i];
	            }
	        }
			 return max;
		 }
	 
	 public static char findMax(Character[] input, Function<Character[], Character> findMax) {
			return findMax.apply(input);
	 }
	 
	public static void main(String[] args) {
		Integer [] intArry = {1, 2, 3, 4, 5 };
		Double [] doubArry = {1.1, 2.2, 3.3, 4.4};
		Character [] charArray = {'H','E','L', 'L', 'O' };
		String [] strArray = {"once", "upon", "a", "time" };
		printArray(intArry);
		printArray(doubArry);
		printArray(charArray);
		printArray(strArray);
		System.out.println("max Integer is: " + getMax(intArry));
		System.out.println("max Double is: " + getMax(doubArry));
		System.out.println("max Character is: " + getMax(charArray));
		System.out.println("max String is: " + getMax(strArray));
		
		Function<Character[], Character> findMax = (Character[] deez) -> 
		{char max = deez[0];
		for (int i = 1; i < deez.length; i++) {
            if ((deez[i].compareTo(max))>0){
                max = deez[i];
            }
        }
		 return max;};
		System.out.println("Max char is (using functional interface): " + findMax(charArray, findMax));
	}

}
