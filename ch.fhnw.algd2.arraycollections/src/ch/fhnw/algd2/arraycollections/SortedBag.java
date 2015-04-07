package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class SortedBag<E extends Comparable<? super E>> extends
		AbstractArrayCollection<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private Object[] data;
	private int size;

	public SortedBag() {
		this(DEFAULT_CAPACITY);
	}

	public SortedBag(int capacity) {
		data = new Object[capacity];
	}

	
	@Override
	public boolean add(E e) {
		if (size == data.length) {
			throw new IllegalStateException("Sorry, wir sind voll");
		}else{
			// Index des hinzuzufügenden Objektes
			int i = indexOf(e) + 1;
			for (int j = size; j != i; j--) {
				data[j] = data[j - 1]; 
			}
			data[i] = e;
			size++;
			return true;
		}
	
	}

	@Override
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (i < 0 || !data[i].equals(o)) {
			return false;
		}else{
			data[i] = null;
			while(i != size - 1) {
				data[i] = data[i + 1];
				i++;
			}
			size--;
			return true;
		}
	}

	@Override
	public boolean contains(Object o) {
		int i = indexOf(o);
		return i>=0 && data[i].equals(o);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return size;
	}
	
	// Indexsuche mit binärer Suche
	private int indexOf(Object o) {
		if (o==null) {
			throw new NullPointerException();
		}else{
			int l = -1;
			int h = size;
			while (h - l > 1) {
				int m = (h + l) / 2;
				if (((E) data[m]).compareTo((E) o) <= 0) {
					l = m;
				}else{
					h = m;
				}
			}return l;
		}
	}

	public static void main(String[] args) {
		SortedBag<Integer> bag = new SortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
