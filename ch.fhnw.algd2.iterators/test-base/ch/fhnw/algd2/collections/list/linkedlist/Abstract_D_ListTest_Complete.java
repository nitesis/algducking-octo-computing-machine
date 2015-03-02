package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Additional Tests: - Order (list-semantic: Tail adding) by reference - add
 * element at specific position (indexoutofbounds check) - remove element from
 * specific position (indexoutofbounds check)
 * 
 * @author Michael
 */
public abstract class Abstract_D_ListTest_Complete extends AbstractMyLinkedListTest {

	/*
	 * Order check
	 */

	@Test
	public void add_Numbers1To5_AddedInOrder() {
		Integer[] numbers = new Integer[] { Integer.valueOf(1), Integer.valueOf(2),
				Integer.valueOf(2), Integer.valueOf(3) };
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
		Integer one = Integer.valueOf(1);
		Integer two = Integer.valueOf(2);
		Integer three = Integer.valueOf(3);
		Integer four = Integer.valueOf(4);
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
		list.add(-1, Integer.valueOf(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void add_TooLargeIndex_ThrowIndexOutOfBoundsException() {
		addNumbersFromOneToFiveToList();
		list.add(list.size() + 1, Integer.valueOf(2));
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
		Integer one = Integer.valueOf(1);
		Integer two = Integer.valueOf(2);
		Integer three = Integer.valueOf(3);
		Integer four = Integer.valueOf(4);
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
	
	
	@Test
	public void removeElementByIndex_EqualElementBeforeInList_SecondElementRemoved(){
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(2);
		list.add(number);
		assertSame(number, list.remove(5));
		checkOrderDependentOccurrence(new Integer[]{1,2,3,4,5});
	}

	/*
	 * Check remove again, order-dependent
	 */
	@Test
	public void remove_ExistingElementAtHead_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(1);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		checkOrderDependentOccurrence(new Integer[] { 2, 3, 4, 5 });
	}

	@Test
	public void remove_ExistingElementAtTail_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(5);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		checkOrderDependentOccurrence(new Integer[] { 1, 2, 3, 4 });
	}

	@Test
	public void remove_ExistingElementInBetween_ElementRemoved() {
		addNumbersFromOneToFiveToList();
		Integer number = Integer.valueOf(3);
		assertTrue(list.remove(number));
		assertFalse(list.contains(number));
		checkOrderDependentOccurrence(new Integer[] { 1, 2, 4, 5 });
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void get_OnIndex0InEmptyList_IndexOutOfBoundsException() {
		list.get(0);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void get_Index5OnListWith5Elements_IndexOutOfBoundsException() {
		addNumbersFromOneToFiveToList();
		list.get(5);
	}

	@Test
	public void get_FirstIndex_FirstElement() {
		Integer one = Integer.valueOf(1);
		Integer[] numbers = new Integer[] { one , Integer.valueOf(2),
				Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5) };
		addNumbersToList(numbers);
		assertSame(one, list.get(0));
	}
	
	@Test
	public void get_MiddleIndex_MiddleElement() {
		Integer three = Integer.valueOf(3);
		Integer[] numbers = new Integer[] { Integer.valueOf(1), Integer.valueOf(2),
				three, Integer.valueOf(4), Integer.valueOf(5) };
		addNumbersToList(numbers);
		assertSame(three, list.get(2));
	}
	
	@Test
	public void get_LastIndex_LastElement() {
		Integer five = Integer.valueOf(5);
		Integer[] numbers = new Integer[] { Integer.valueOf(1), Integer.valueOf(2),
				Integer.valueOf(5), Integer.valueOf(4), five };
		addNumbersToList(numbers);
		assertSame(five, list.get(4));
	}
	
	
	
	private void checkOrderByReference(Integer[] numbers) {
		assertEquals(list.size(), numbers.length);
		Object[] listArray = list.toArray();
		for (int i = 0; i < numbers.length; i++) {
			assertSame(
					"Please add elements at the end of the list (default behaviour for lists add method)",
					numbers[i], listArray[i]);
		}
	}

}
