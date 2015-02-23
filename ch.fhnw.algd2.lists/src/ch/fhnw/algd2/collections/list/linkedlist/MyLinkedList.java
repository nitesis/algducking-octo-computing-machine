package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class MyLinkedList<E> extends MyAbstractList<E> {
	private int size = 0;
	private Node<E> first;

	@Override
	public boolean add(E e) {
		// TODO implement this operation (part A)
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		// TODO implement this operation (part C)
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		// TODO implement this operation (part B)
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first;
		while (current != null) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	private static class Node<E> {
		private final E elem;
		private Node<E> next;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(Arrays.toString(list.toArray()));
	}
}
