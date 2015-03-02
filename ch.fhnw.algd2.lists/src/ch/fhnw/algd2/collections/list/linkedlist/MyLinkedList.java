package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class MyLinkedList<E> extends MyAbstractList<E> {
	private int size = 0;
	private Node<E> first;
	private Node<E> last;
	

	@Override
	public boolean add(E e) {

		if (e == null) {
			throw new NullPointerException("null is not allowed");
		}
			
		if (first != null) {
			last.next = new Node<E> (e);
			last = last.next;
		} else {
			first = new Node<E> (e);
			last = first;
		}
		size ++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {

		if (o != null && contains(o)) {
			if (first.next == null) {
				o = null;
//				first = new Node<E> (null);
			} else {
				o = last;
				last = null;
			}
			size--;
			last.next = null;
			return true;
		}
		return false;
	}
	
	public int indexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		Object [] temp = this.toArray();
		int i = size - 1;		
		while (i >= 0 && !temp[i].equals(o)) {
			i--; 
		} 
		return i;
	}

	@Override
	public E remove(int index) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {

		Node <E> temp = first;	
		if (o == null) {
			throw new NullPointerException();
		}
		while (temp != null && !temp.elem.equals(o)) {
			temp = temp.next;
		}
		return (temp != null);
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
