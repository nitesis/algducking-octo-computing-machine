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
		if (o.equals(null)) {
			throw new IllegalStateException("Uups");
		}
		else if (contains(o)) {
			for (int i = 0; i < data.length; i++) {
				if (o.equals(data[i])) {
					data[i] = data[i+1];
				}
			}
			size--;
		}
		return contains(o);
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

	public static void main(String[] args) {
		UnsortedSet<Integer> bag = new UnsortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
