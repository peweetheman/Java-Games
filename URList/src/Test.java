import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		URList<Integer> list = new URArrayList<>();
		
		List<Integer> list8 = new ArrayList<>();

		list8.add(0, 0);
		list.add(0, 3);
		list8.add(0, 0);
		
		URList<Integer> link = new URArrayList<>();
		link.add(1);
		link.add(2);
		link.add(3);
		link.add(4);
		System.out.print("Link List: " + link.size());

	//	link.add(0, 0);
		link.add(6);
		URList<Integer> sub = link.subList(2, 4);
		link.set(5, 0);
		System.out.println(link.remove(new Integer(7)));
		

		System.out.println("");
		
		List<Integer> list2 = new ArrayList<>();
		list2.add(3);
		list2.add(4);
		list2.add(2);
		list2.add(1);
		Iterator<Integer> itr = sub.iterator();
		System.out.print("Sub List: ");
		while(itr.hasNext()) {
			System.out.print(" " + itr.next() + " ");
		}
		System.out.println("");

		Object[] arr = list2.toArray();
		
		for(int i = 0; i<arr.length; i++) {
			System.out.print(" " + arr[i]);
		}
		System.out.println("length list 2: " + arr.length);
		link.removeAll(list2);
		Iterator<Integer> itr0 = link.iterator();
		System.out.print("Link List0: ");
		while(itr0.hasNext()) {
			System.out.print(" " + itr0.next() + " ");
		}
		System.out.println("");


		System.out.print("Link List: " + link.size());

		System.out.println("");
		
		link.set(0, 0);
		System.out.println("get: " + link.get(1));
		
		Iterator<Integer> itr5 = link.iterator();
		System.out.print("Link List: ");
		while(itr5.hasNext()) {
			System.out.print(" " + itr5.next() + " ");
		}
		System.out.println("");

		Iterator<Integer> it2 = list8.iterator();
		System.out.print("Array List8: ");

		while(it2.hasNext()) {
			System.out.print(" " + it2.next() + " ");
		}
		System.out.println("");
		System.out.println(link.equals(list8));
		
		Iterator<Integer> it = list.iterator();
		System.out.print("Array List: ");

		while(it.hasNext()) {
			System.out.print(" " + it.next() + " ");
		}
	}

}
