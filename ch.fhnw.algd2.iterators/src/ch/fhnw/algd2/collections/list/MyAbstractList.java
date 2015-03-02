package ch.fhnw.algd2.collections.list;

import java.util.AbstractList;
import java.util.Iterator;

public abstract class MyAbstractList<E> extends AbstractList<E> {
	@Override
	public abstract boolean add(E e);

	@Override
	public abstract boolean remove(Object o);

	@Override
	public abstract boolean contains(Object o);

	@Override
	public abstract E get(int index);

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException(
				"Iterator not supported. Call toArray().");
	}
}
