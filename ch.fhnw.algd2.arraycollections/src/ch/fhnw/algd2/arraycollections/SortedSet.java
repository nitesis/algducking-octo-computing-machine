package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Set;

public class SortedSet<E extends Comparable<? super E>> extends
		AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private Object[] data;
	private int size = 0;

	public SortedSet() {
		this(DEFAULT_CAPACITY);
	}

	public SortedSet(int capacity) {
		data = new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		if (size == data.length) {
			throw new IllegalStateException("Sorry, wir sind voll");
		} else {
		int i = indexOf(e);
		if (i >= 0) {
			return false;
		} else if (e == null){
			throw new NullPointerException();
		} else {
			i = -(i + 1); // insertion point
			for (int j = size; j != i; j--) {
				data[j] = data[j-1];
			}
			size++;
			data[i] = e;
			return true;
		}
	}
	}	

	@Override
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (i < 0) {
			return false;
		} else {
			data[i] = null;
			while (i != size - 1) {
				data[i] = data[i + 1];
				i++;
			}
		}size--;
		return true;
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
	
	private int indexOf(Object o) {
		return Arrays.binarySearch(data, 0, size, o);
//		if (o==null) {
//			throw new NullPointerException();
//		}else{
//		int l = -1;
//		int h = size;
//			while (h - l > 1) {
//				int m = (l + h) / 2;
//				if (((E)data[m]).compareTo((E)o) <= 0) {
//					l = m;
//				}else{
//					h = m;
//				}
//			}return l;
//		}
	}

	public static void main(String[] args) {
		SortedSet<Integer> bag = new SortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
