package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public abstract class AbstractMyLinkedListTest {

	protected MyAbstractList<Integer> list;

	@Before
	public void init() {
		list = getListInstance();
	}

	protected abstract MyAbstractList<Integer> getListInstance();

	protected void addNumbersToList(Integer[] numbersToAdd) {
		int counter = 0;
		for (Integer number : numbersToAdd) {
			++counter;
			assertTrue(list.add(number));
		}
		assertEquals(counter, list.size());
	}

	protected void checkOrderIndependentOccurrence(Integer[] numbersToAdd) {
		Collection<Integer> expected = new LinkedList<Integer>(
				Arrays.asList(numbersToAdd));
		Object[] listArray = list.toArray();
		assertEquals(expected.size(), listArray.length);
		for (int i = 0; i < listArray.length; i++) {
			assertTrue(expected.remove(listArray[i]));
		}
		assertEquals(0, expected.size());
	}

	protected void checkOrderDependentOccurrence(Integer[] numbersToAdd) {
		Object[] listArray = list.toArray();
		assertEquals(numbersToAdd.length, listArray.length);
		for (int i = 0; i < listArray.length; i++) {
			assertEquals(numbersToAdd[i], listArray[i]);
		}
	}

	protected void addNumbersFromOneToFiveToList() {
		Integer[] numbersToAdd = getArrayWithNumbersOneToFive();
		addNumbersToList(numbersToAdd);
	}

	protected Integer[] getArrayWithNumbersOneToFive() {
		int end = 5;
		Integer[] numbersToAdd = new Integer[end];
		for (int i = 1; i <= end; i++) {
			numbersToAdd[i - 1] = i;
		}
		return numbersToAdd;
	}

}
