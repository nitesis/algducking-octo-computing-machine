package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import ch.fhnw.algd2.collections.list.linkedlist.general.AbstractMyLinkedListTest;

/**
 * Additional Tests: - Order (list-semantic: Tail adding) by reference - add
 * element at specific position (indexoutofbounds check) - remove element from
 * specific position (indexoutofbounds check)
 * 
 * @author Michael
 */
public class D_MyLinkedListTest_Complete extends AbstractMyLinkedListTest {

	/*
	 * Order check
	 */

	@Test
	public void add_Numbers1To5_AddedInOrder() {
		Integer[] numbers = new Integer[] { new Integer(1), new Integer(2),
				new Integer(2), new Integer(3) };
		addNumbersToList(numbers);
		checkOrderByReference(numbers);
	}

	/*
	 * Add element at specific position
	 */

	@Test
	public void add_Numbers5To1AtIndex0InSequence_NumbersInAscendingOrder() {
		Integer[] numbers = getArrayWithNumbersOneToFive();
		for (int i = numbers.length - 1; i >= 0; i--) {
			list.add(0, numbers[i]);
		}
		assertEquals(numbers.length, list.size());
		checkOrderByReference(numbers);
	}

	@Test
	public void add_Numbers1To5AtLastIndexInSequence_NumbersInAscendingOrder() {
		Integer[] numbers = getArrayWithNumbersOneToFive();
		for (int i = 0; i < numbers.length; i++) {
			list.add(list.size(), numbers[i]);
		}
		assertEquals(numbers.length, list.size());
		checkOrderByReference(numbers);
	}

	@Test
	public void add_ElementAtIndex2_AddedAtIndex2() {
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		Integer three = new Integer(3);
		Integer four = new Integer(4);
		Integer[] numbers = new Integer[] { one, two, three, four };
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		Integer secondTwo = new Integer(2);
		list.add(2, secondTwo);
		Integer[] expectedNumbers = new Integer[] { one, two, secondTwo, three,
				four };
		assertEquals(expectedNumbers.length, list.size());
		checkOrderByReference(expectedNumbers);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void add_NegativeIndex_ThrowIndexOutOfBoundsException() {
		list.add(-1, new Integer(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void add_TooLargeIndex_ThrowIndexOutOfBoundsException() {
		addNumbersFromOneToFiveToList();
		list.add(list.size() + 1, new Integer(2));
	}

	/*
	 * Remove element at specific position
	 */

	@Test
	public void remove_Index0WhenNumbers1To5Added_Element1Removed() {
		Integer[] numbers = getArrayWithNumbersOneToFive();
		addNumbersToList(numbers);
		assertEquals(numbers.length, list.size());
		Integer[] withoutFirstNumber = Arrays.copyOfRange(numbers, 1,
				numbers.length);
		list.remove(0);
		checkOrderByReference(withoutFirstNumber);
	}

	@Test
	public void remove_ElementAtLastIndexWhenNumbers1To5Added_Element5Removed() {
		Integer[] numbers = getArrayWithNumbersOneToFive();
		addNumbersToList(numbers);
		assertEquals(numbers.length, list.size());
		Integer[] withoutFirstNumber = Arrays.copyOfRange(numbers, 0,
				numbers.length - 1);
		list.remove(list.size() - 1);
		checkOrderByReference(withoutFirstNumber);
	}

	@Test
	public void remove_ElementAtIndex2WhenNumbers1To4Added_Element3Removed() {
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		Integer three = new Integer(3);
		Integer four = new Integer(4);
		Integer[] numbers = new Integer[] { one, two, three, four };
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		assertEquals(numbers.length, list.size());
		list.remove(2);
		Integer[] expectedNumbers = new Integer[] { one, two, four };
		assertEquals(expectedNumbers.length, list.size());
		checkOrderByReference(expectedNumbers);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void remove_NegativeIndex_IndexOutOfBoundsException() {
		list.remove(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void remove_TooLargeIndex_ThrowIndexOutOfBounds() {
		addNumbersFromOneToFiveToList();
		list.remove(list.size());
	}

	/*
	 * Check remove again, order-dependent
	 */
	@Test
	public void remove_ExistingElementAtHead_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = new Integer(1);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		checkOrderDependentOccurrence(new Integer[] { 2, 3, 4, 5 });
	}

	@Test
	public void remove_ExistingElementAtTail_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = new Integer(5);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		checkOrderDependentOccurrence(new Integer[] { 1, 2, 3, 4 });
	}

	@Test
	public void remove_ExistingElementInBetween_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = new Integer(3);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		checkOrderDependentOccurrence(new Integer[] { 1, 2, 4, 5 });
	}

	private void checkOrderByReference(Integer[] numbers) {
		assertEquals(list.size(), numbers.length);
		for (int i = 0; i < numbers.length; i++) {
			//TODO: To Array
			assertSame(
					"Please add elements at the end of the list (default behaviour for lists add method)",
					numbers[i], list.toArray()[i]);
		}
	}

}
