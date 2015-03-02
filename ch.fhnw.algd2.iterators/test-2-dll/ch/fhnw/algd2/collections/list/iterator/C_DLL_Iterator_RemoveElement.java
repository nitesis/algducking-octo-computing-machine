package ch.fhnw.algd2.collections.list.iterator;

import ch.fhnw.algd2.collections.list.DoublyLinkedListFactory;
import ch.fhnw.algd2.collections.list.MyAbstractList;

public class C_DLL_Iterator_RemoveElement extends
		Abstract_C_ListIterator_RemoveElement {

	@Override
	protected MyAbstractList<Integer> createInstance() {
		return DoublyLinkedListFactory.createInstance();
	}

}
