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
		if (e == null) {
			throw new NullPointerException("no value");
		}
		
		int i;
		if (size < data.length) {
//			Falls Hinzufügen möglich, wird size um 1 vergrössert
			size++;
//			Falls e noch nicht vorhanden, wird i durch Umformen errechnet
			if (Arrays.binarySearch(data, 0, data.length, e) < 0){				
				i = (Arrays.binarySearch(data, 0, data.length, e) + 1) * (-1);
//			Falls e bereits vorhanden, wird i direkt durch Methodenaufruf zurückgegeben	
			} else {
				i = Arrays.binarySearch(data, 0, data.length, e);
			}
//			Es werden alle Elemente nach i um eins nach hinten verschoben, um Platz zu machen für e
//			Anschliessend wird e an Index i eingesetzt
			int n = data.length;
			for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && (((Comparable<? super E>) data[j - 1]).compareTo((E) data[j]) > 0); j--) { 
				Object t = data[j - 1]; 
				data[j - 1] = data[j]; 
				data[j] = t;
			}
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (contains(o)) {
			int n = data.length;
			for (int i = 1; i < n; i++) {
				for (int j = i; j > 0 && (((Comparable<? super E>) data[j - 1]).compareTo((E) data[j]) > 0); j--) { 
					Object temp = data[j - 1]; 
					data[j - 1] = data[j]; 
					data[j] = temp;
				}
			}
			
			size--;
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		int i = 0;
		while (i < data.length  && data[i].equals(o)) {
			i++;
		}
		return (i < data.length);
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
		SortedBag<Integer> bag = new SortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
