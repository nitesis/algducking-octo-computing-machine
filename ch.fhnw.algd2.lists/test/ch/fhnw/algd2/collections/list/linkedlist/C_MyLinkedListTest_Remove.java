package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.fhnw.algd2.collections.list.linkedlist.general.AbstractMyLinkedListTest;

public class C_MyLinkedListTest_Remove extends AbstractMyLinkedListTest {

	@Test
	public void remove1_Numbers1To5Added_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = new Integer(1);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		assertEquals(4, list.size());
		checkOrderIndependentOccurrence(new Integer[] { 2, 3, 4, 5 });
	}

	@Test
	public void remove5_Number1To5Added_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = new Integer(5);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		assertEquals(4, list.size());
		checkOrderIndependentOccurrence(new Integer[] { 1, 2, 3, 4 });
	}

	@Test
	public void remove3_Numbers1To5Added_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = new Integer(3);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		assertEquals(4, list.size());
		checkOrderIndependentOccurrence(new Integer[] { 1, 2, 4, 5 });
	}

	@Test
	public void remove10_Numbers1To5Added_NoCollectionChanges() {
		addNumbersFromOneToFiveToList();
		assertFalse(list.remove(new Integer(10)));
		assertEquals(5, list.size());
	}

	@Test
	public void remove10_EmptyList_NoCollectionChanges() {
		assertFalse(list.remove(new Integer(10)));
	}

}
