package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public abstract class Abstract_C_ListTest_Remove extends AbstractMyLinkedListTest {

	@Test
	public void remove1_Numbers1To5Added_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(1);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		assertEquals(4, list.size());
		checkOrderIndependentOccurrence(new Integer[] { 2, 3, 4, 5 });
	}

	@Test
	public void remove5_Number1To5Added_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(5);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		assertEquals(4, list.size());
		checkOrderIndependentOccurrence(new Integer[] { 1, 2, 3, 4 });
	}

	@Test
	public void remove3_Numbers1To5Added_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(3);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		assertEquals(4, list.size());
		checkOrderIndependentOccurrence(new Integer[] { 1, 2, 4, 5 });
	}

	@Test
	public void remove10_Numbers1To5Added_NoCollectionChanges() {
		addNumbersFromOneToFiveToList();
		assertFalse(list.remove(Integer.valueOf(10)));
		assertEquals(5, list.size());
	}

	@Test
	public void remove10_EmptyList_NoCollectionChanges() {
		assertFalse(list.remove(Integer.valueOf(10)));
	}
	
	@Test
	public void remove_WhenDuplicatesExist_OnlyOneElement(){
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(2);
		list.add(number);
		assertTrue(list.remove(number));
		checkOrderIndependentOccurrence(new Integer[]{1,3,4,5,2});
	}

}
