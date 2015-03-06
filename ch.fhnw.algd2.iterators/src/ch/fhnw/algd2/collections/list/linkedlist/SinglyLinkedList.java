package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class SinglyLinkedList<E> extends MyAbstractList<E> {
	// variable int modCount already declared by AbstractList<E>
	private int size = 0;
	private Node<E> first, last;
	
	private int countL = 0;

	@Override
	public boolean add(E e) {
		Node<E> n = new Node<E>(e, null);
		if (last != null) last.next = n;
		else first = n;
		last = n;
		size++;
		countL++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (index == 0) {
			first = new Node<E>(element, first);
			if (last == null) last = first;
		} else if (index == size) {
			last.next = new Node<E>(element, null);
			last = last.next;
		} else {
			Node<E> n = first;
			while (index > 1) {
				n = n.next;
				index--;
			}
			n.next = new Node<E>(element, n.next);
		}
		size++;
		countL++;
	}

	@Override
	public boolean remove(Object o) {
		Node<E> n = first, p = null;
		while (n != null && n.elem != o && (n.elem == null || !n.elem.equals(o))) {
			p = n;
			n = n.next;
		}
		if (n != null) {
			if (n == last) last = p;
			if (p != null) p.next = n.next;
			else first = n.next;
			size--;
			countL++;
			return true;
		} else return false;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		E e;
		if (index == 0) {
			e = first.elem;
			first = first.next;
			if (first == null) last = null;
		} else {
			Node<E> n = first;
			while (index > 1) {
				n = n.next;
				index--;
			}
			e = n.next.elem;
			n.next = n.next.next;
			if (n.next == null) last = n;
		}
		size--;
		countL++;
		return e;
	}

	@Override
	public boolean contains(Object o) {
		Node<E> n = first;
		while (n != null && n.elem != o && (n.elem == null || !n.elem.equals(o))) {
			n = n.next;
		}
		return n != null;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		Node<E> n = first;
		while (index > 0) {
			n = n.next;
			index--;
		}
		return n.elem;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> n = first;
		while (n != null) {
			array[index++] = n.elem;
			n = n.next;
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

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<E> {
		Node<E> next = first;
		private int countI = countL;
		
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public E next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			if (countI != countL) {
				throw new ConcurrentModificationException();
			}
			E e = next.elem;
			next = next.next;
			return e;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(Arrays.toString(list.toArray()));
	}
}
