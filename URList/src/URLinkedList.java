import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
//Patrick Phillips, Partner: Brandon Toops

public class URLinkedList<E> implements URList<E>, Iterable<E> {

	private URNode<E> head;
	private URNode<E> tail;
	private int size;


	public URLinkedList(){
		head = new URNode<E>(null, null, tail);
		tail = new URNode<E>(null, head, null);
		size = 0;
	}


	public URLinkedList(Collection<? extends E> c) {
		addAll(c);
	}	

	@Override
	public boolean add(E e) {
		URNode<E> temp = tail.prev();
		URNode<E> newNode = new URNode<E>(e, temp, tail);
		tail.setPrev(newNode);
		temp.setNext(newNode);
		size++;
		return true;
	}
	@Override
	public void add(int index, E element) {
		if(index!=0) {
			checkRange(index-1);
		}
		URNode<E> temp = head;
		for(int i = 0; i<index; i++) {
			temp = temp.next();
		}
		URNode<E> node = new URNode<E>(element, temp, temp.next());
		temp.next().setPrev(node);
		temp.setNext(node);
		size++;
	}
	public void checkRange(int index) {
		System.out.println();
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Out of Bounds! Index: " + index + " Size: " + size);
		}
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] stuff = c.toArray();
		for(int i = 0; i < stuff.length; i ++) {
			add((E) stuff[i]);
		}
		return true;
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		checkRange(index);
		Object[] stuff = c.toArray();
		for(int i = 0; i < stuff.length; i ++) {
			add(index + i, (E) stuff[i]);
		}
		return true;
	}
	@Override
	public void clear() {
		head = new URNode<E>(null, null, tail);
		tail = new URNode<E>(null, head, null);
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		URNode<E> temp = head.next();
		for(int i = 0; i<size; i++) {
			if(o.equals(temp.element())) {
				return true;
			}
			temp = temp.next();
		}
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		Object[] stuff = c.toArray();
		for(int i = 0; i<stuff.length; i++) {
			if(!contains(stuff[i])) {
				return false;
			}
		}
		return true;
	}
	@Override
	public E get(int index) {
		checkRange(index);
		URNode<E> temp = head.next();
		for( int i = 0; i< index; i++) {
			temp = temp.next();
		}
		return temp.element();
	}
	@Override
	public int indexOf(Object o) {
		URNode<E> temp = head.next();
		for(int i = 0; i<size; i++) {
			if(temp.element().equals(o)) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public boolean isEmpty() {
		if(head.next()!=null) {
			return false;
		}
		return true;
	}
	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}
	public class MyIterator implements Iterator<E>{
		URNode<E> first = head.next();

		@Override
		public boolean hasNext() {
			return (first!=null&&first.element()!=null);
		}

		@Override
		public E next() {
			if(hasNext()) {
				E element = first.element();
				first=first.next();
				return element;
			}else {
				throw new NoSuchElementException("That doesn't exist!");
			}
		}

	}
	@Override
	public E remove(int index) {
		checkRange(index);
		URNode<E> temp = head.next();
		for(int i = 0; i<index; i++) {
			temp = temp.next();
		}
		temp.prev().setNext(temp.next());
		temp.next().setPrev(temp.prev());
		E element = temp.element();
		//temp.setElement(null);
		size--;
		return element;
	}
	@Override
	public boolean remove(Object o) {
		URNode<E> temp = head.next();
		for(int i = 0; i<size; i++) {
			if(o.equals(temp.element())) {
				remove(i);
				return true;
			}
			temp = temp.next();
		}
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		Object[] col = c.toArray();
		boolean check = false;
		for(int i = 0; i<col.length; i++) {
			if(remove(col[i])) {
				check = true;
			}
		}
		return check;
	}
	@Override
	public E set(int index, E element) {
		checkRange(index);
		URNode<E> temp = head.next();
		for(int i = 0; i<index; i++) {
			temp = temp.next();
		}
		E elem = temp.element();
		temp.setElement(element);
		return elem;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public URList<E> subList(int fromIndex, int toIndex) {
		checkRange(fromIndex);
		checkRange(toIndex);
		URLinkedList<E> sub = new URLinkedList<E>();
		for(int i = 0; i< toIndex-fromIndex; i++) {
			sub.add(get(i+fromIndex));
		}
		return sub;
	}

	public boolean equals(Object o) {
		if(o instanceof URList) {
			Object[] temp = ((URList<E>) o).toArray();
			if(temp.length!=size) {
				return false;
			}
			for(int i = 0; i< size; i++) {
				if(!(temp[i].equals(get(i)))){
					return false;
				}
			}
			return true;
		}
		else if(o instanceof Collection){
			Object[] temp = ((Collection) o).toArray();
			if(temp.length!=size) {
				return false;
			}
			for(int i = 0; i< size; i++) {
				if(!(temp[i].equals(get(i)))){
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Object[] toArray() {
		Iterator<E> itr = this.iterator();
		Object[] arr = new Object[size];
		for(int i =0; i<size; i++) {
			arr[i] = itr.next();
		}
		return arr;
	}
	// Inserts the specified element at the beginning of this list.
	public void addFirst(E e) {
		add(0, e);
	}
	// Appends the specified element to the end of this list.
	public void addLast(E e) {
		add(e);
	}
	// Retrieves, but does not remove, the first element of this list, or returns null if
	public E peekFirst() {
		return head.next().element();
	}
	// Retrieves, but does not remove, the last element of this list, or returns null if
	public E peekLast() {
		return tail.prev().element();
	}
	// Retrieves and removes the first element of this list, or returns null if this list is empty
	public E pollFirst() {
		if(isEmpty()) {
			return null;
		}
		else {
			E elem = head.next().element();
			remove(0);
			return elem;
		}
	}
	// Retrieves and removes the last element of this list, or returns null if this list is empty
	public E pollLast() {
		if(isEmpty()) {
			return null;
		}
		else {
			E elem = tail.prev().element();
			remove(size-1);
			return elem;
		}
	}


}
