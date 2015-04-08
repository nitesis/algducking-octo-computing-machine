package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class UnsortedBag<E> extends AbstractArrayCollection<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private Object[] data;
	private int size = 0;

	public UnsortedBag() {
		this(DEFAULT_CAPACITY);
	}

	public UnsortedBag(int capacity) {
		data = new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		if (size == data.length) {
			throw new IllegalStateException("Collection is full.");
		}else if(e == null){
			throw new NullPointerException();
		}else{
			data[size] = e;
			size++;
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (i < 0){
			return false;
		}
		else {
			data[i] = data[size-1];
			data[size-1] = null;
			size--;
			
		}
		return true;
	}

	@Override
	public boolean contains(Object o) {
		return (indexOf(o) >= 0);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return size;
	}
	
	public int indexOf(Object o) {
		if (o==null) throw new NullPointerException();
		else{
			int i = size - 1;
			// sucht o mit dem grössten Index
			while (i >= 0 && !data[i].equals(o)){
				i--;
			}
			return i;
		}
	}

	public static void main(String[] args) {
		UnsortedBag<Integer> bag = new UnsortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
