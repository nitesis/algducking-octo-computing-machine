package ch.fhnw.algd2.collections.list.linkedlist;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class SLL_C_Test_Remove extends Abstract_C_ListTest_Remove {
	@Override
	protected MyAbstractList<Integer> getListInstance() {
		return new SinglyLinkedList<Integer>();
	}

}
