package ch.fhnw.algd2.collections.list.iterator;

import ch.fhnw.algd2.collections.list.MyAbstractList;
import ch.fhnw.algd2.collections.list.linkedlist.SinglyLinkedList;

/**
 * Checks simple iteration over empty and integer list.
 * 
 * @author Michael
 */
public class A_SLL_Iterator_SimpleIteration extends
		Abstract_A_ListIterator_SimpleIteration {

	@Override
	protected MyAbstractList<Integer> createInstance() {
		return new SinglyLinkedList<Integer>();
	}

}
