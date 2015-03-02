package ch.fhnw.algd2.collections.list.linkedlist;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class SLL_B_Test_Contains extends Abstract_B_ListTest_Contains {

	@Override
	protected MyAbstractList<Integer> getListInstance() {
		return new SinglyLinkedList<Integer>();
	}
	
}
