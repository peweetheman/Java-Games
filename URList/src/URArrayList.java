import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
//Patrick Phillips, Partner: Brandon Toops
public class URArrayList<E> implements URList<E>, Iterable<E> {

	private Object[] elementData;
	private int size;

	public URArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else if (initialCapacity == 0) {
			this.elementData = null;
		} else {
			throw new IllegalArgumentException("You Can't do That: "+initialCapacity);
		}
		size = 0;
	}

	public URArrayList() {
		this.elementData = new Object[10];
		size = 0;
	}
	public URArrayList(Collection<? extends E> e) {
		this.elementData = e.toArray();
		size = e.size();
	}
	// Increases the capacity of this ArrayList instance, if necessary,
	// to ensure that it can hold at least the number of elements specified
	// by the minimum capacity argument.
	public void ensureCapacity(int minCapacity) {
		if(elementData.length<minCapacity) {
			int oldCapacity = elementData.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			elementData = Arrays.copyOf(elementData, newCapacity);
			if(newCapacity<minCapacity) {
				newCapacity = minCapacity;
			}
		}
	}

	public int getCapacity() {
		return elementData.length;
	}



	@Override
	public boolean add(E e) {
		ensureCapacity(size+1);
		elementData[size++] = e;
		return true;
	}

	@Override
	public void add(int index, E element) {
		checkBounds(index);
		ensureCapacity(size+1);
		for(int i = size-1; i>=index; i--) {
			elementData[i+1] = elementData[i];
		}
		elementData[index] = element;
		size++;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		ensureCapacity(size + c.size());
		Object temp[] = c.toArray();
		for(int i=0; i<c.size(); i++) {
			elementData[size++] = temp[i];
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		checkBounds(index);
		ensureCapacity(size + c.size());
		Object temp[] = c.toArray();
		for(int i=size+c.size(); i>index; i--) {
			elementData[i+1] = elementData[i];
		}
		for(int i = 0; i<c.size(); i++) {
			elementData[i+size] = temp[i];
		}
		size = size+ c.size();
		return true;
	}

	@Override
	public void clear() {
		elementData = new Object[10];
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> itr = c.iterator();
		while(itr.hasNext()) {
			Object element = itr.next();
			if(contains(element)) {
				itr.remove();
			}
			else {
				return false;
			}
		}
		return true;
	}
	public void checkBounds(int index) {
		if(index>size || size<0) {
			throw new IndexOutOfBoundsException("No Element at Index: " + index);
		}
	}
	@Override
	public E get(int index) {
		checkBounds(index);
		return (E) elementData[index];
	}

	@Override
	public int indexOf(Object o) {
		for (int i=0; i<size; i++) {
			if (o.equals(elementData[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		if(size ==0)
			return true;
		else
			return false;
	}

	@Override
	public Iterator<E> iterator() {

		return new MyIterator();
	}
	public class MyIterator implements Iterator<E>{

		int i = 0;
		int end;

		@Override
		public boolean hasNext() {
			if(elementData[i] !=null) {
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public E next() {
			if(hasNext()) {
				i++;
				return (E) elementData[i-1];
			}
			else {
				throw new NoSuchElementException();
			}

		}
	}

	@Override
	public E remove(int index) {
		checkBounds(index);
		E value = (E) elementData[index];
		int shift = size - index - 1;
		System.arraycopy(elementData, index+1, elementData, index, shift);
		size--;
		elementData[size] = null;
		return value;
	}

	@Override
	public boolean remove(Object o) {
		for(int i = 0; i<size; i++) {
			if(o.equals(elementData[i])) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Object temp[] = c.toArray();
		Object temp2[] = elementData;
		for(int i = 0; i<temp.length; i++) {
			if(contains(temp[i])) {
				remove(temp[i]);
			}
		}
		if(temp2.equals(elementData)) {
			return false;
		}
		return true;
	}

	@Override
	public E set(int index, E element) {
		checkBounds(index);
		elementData[index] = element;
		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public URList<E> subList(int fromIndex, int toIndex) {
		checkBounds(fromIndex);
		checkBounds(toIndex-1);
		URList<E> subList = new URArrayList<E>();
		for(int i = fromIndex; i<toIndex; i++) {
			subList.add((E) elementData[i]);
		}
		return subList;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		for(int i = 0; i<size; i++) {
			arr[i] = elementData[i];
		}
		return arr;
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

}
