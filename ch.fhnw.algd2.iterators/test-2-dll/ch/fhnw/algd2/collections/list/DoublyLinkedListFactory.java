package ch.fhnw.algd2.collections.list;

import ch.fhnw.algd2.collections.list.linkedlist.DoublyLinkedList;

public final class DoublyLinkedListFactory {

	private DoublyLinkedListFactory() {
	}

	public static final MyAbstractList<Integer> createInstance() {
		return createDoublyLinkedList();
	}

	private static MyAbstractList<Integer> createDoublyLinkedList() {
		return new DoublyLinkedList<Integer>();
	}

}
