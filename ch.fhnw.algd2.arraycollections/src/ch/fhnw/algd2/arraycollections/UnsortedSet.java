package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Set;

public class UnsortedSet<E> extends AbstractArrayCollection<E> implements
		Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private Object[] data;
	private int size;

	public UnsortedSet() {
		this(DEFAULT_CAPACITY);
	}

	public UnsortedSet(int capacity) {
		data = new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		if (!(size < DEFAULT_CAPACITY)) {
			throw new IllegalStateException("Kein Platz mehr!");
		}
		if (!contains(e)) {
			data[size++] = e;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (o == null) {
			throw new NullPointerException("Uups");
		}
		else if (i < 0) {
			return false;
		} else {
			data[i] = data[size - 1];
			data[size - 1] = null;
			size--;
			return true;
		}	
		
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < data.length; i++) {
			if (o.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return size;
	}
	
	private int indexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		} else {
			int i = size - 1;
//			sucht o mit dem kleinsten Index
			while (i >= 0 && !data[i].equals(o) ) {
				i--;
			}
			return i;
		}
	}

	public static void main(String[] args) {
		UnsortedSet<Integer> bag = new UnsortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
