package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class DoublyLinkedList<E> extends MyAbstractList<E> {
	// variable int modCount already declared by AbstractList<E>
	private int size = 0;
	private Node<E> first, last;

	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		return arrayForDoublyLinkedList();
		// return arrayForCyclicDoublyLinkedList();
	}

	private Object[] arrayForDoublyLinkedList() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first;
		while (current != null) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	private Object[] arrayForCyclicDoublyLinkedList() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first.next;
		while (current != first) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException("Implement later");
	}

	private static class Node<E> {
		private E elem;
		private Node<E> prev, next;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(Node<E> prev, E elem, Node<E> next) {
			this.prev = prev;
			this.elem = elem;
			this.next = next;
		}
	}

	private class MyListIterator implements Iterator<E> {
		@Override
		public boolean hasNext() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E next() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(Arrays.toString(list.toArray()));
	}
}
