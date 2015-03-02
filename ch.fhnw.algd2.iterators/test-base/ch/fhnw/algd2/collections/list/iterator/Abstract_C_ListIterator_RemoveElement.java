package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Test;

public abstract class Abstract_C_ListIterator_RemoveElement extends
		AbstractListIteratorTest {

	private Integer one = Integer.valueOf(1);
	private Integer two = Integer.valueOf(2);
	private Integer three = Integer.valueOf(3);
	private Integer four = Integer.valueOf(4);
	private Integer five = Integer.valueOf(5);

	private Integer[] allNumbers = new Integer[] { one, two, three, four, five };

	@Test
	public void removeFirst_OneToFiveInList_TwoToFiveRemaining() {
		addElementsToList();
		removeElementAtPositionWithIterator(0, one);
		assertRemainingElements(new Integer[] { two, three, four, five });
	}

	private void assertRemainingElements(Integer[] integers) {
		Iterator<Integer> it = getIterator();
		for (Integer i : integers) {
			assertTrue(it.hasNext());
			assertSame(i, it.next());
		}
		assertFalse(it.hasNext());
	}

	@Test
	public void removeSecond_OneToFiveInList_RestRemaining() {
		addElementsToList();
		removeElementAtPositionWithIterator(1, two);
		assertRemainingElements(new Integer[] { one, three, four, five });
	}

	@Test
	public void removeThrid_OneToFiveInList_RestRemaining() {
		addElementsToList();
		removeElementAtPositionWithIterator(2, three);
		assertRemainingElements(new Integer[] { one, two, four, five });
	}

	@Test
	public void removeFourth_OneToFiveInList_RestRemaining() {
		addElementsToList();
		removeElementAtPositionWithIterator(3, four);
		assertRemainingElements(new Integer[] { one, two, three, five });
	}

	@Test
	public void removeLast_OneToFiveInList_OneToFourRemaining() {
		addElementsToList();
		removeElementAtPositionWithIterator(4, five);
		assertRemainingElements(new Integer[] { one, two, three, four });
		assertSame(list.get(list.size() - 1), four);
	}

	@Test(expected = IllegalStateException.class)
	public void remove_BeforeFirstNextCall_IllegalStateException() {
		addElementsToList();
		Iterator<Integer> it = getIterator();
		it.remove();
		fail("Remove call should fail");
	}

	@Test
	public void removeTwice_NoNextCallInBetween_IllegalStateException() {
		addElementsToList();
		Iterator<Integer> it = getIterator();
		assertSame(one, it.next());
		assertSame(two, it.next());
		assertSame(three, it.next());
		// First call must pass
		it.remove();
		// Second call must fail
		try {
			it.remove();
			fail("Second remove call must fail");
		} catch (IllegalStateException ex) {
			// OK
		}
	}
	
	@Test
	public void removeTwice_NextCallInBetween_ElementsRemoved() {
		addElementsToList();
		Iterator<Integer> it = getIterator();
		assertSame(one, it.next());
		assertSame(two, it.next());
		assertSame(three, it.next());
		it.remove();
		assertSame(four, it.next());
		it.remove();
		assertSame(five, it.next());
		assertFalse(it.hasNext());
		assertRemainingElements(new Integer[] { one, two, five });
	}

	@Test
	public void remove_OneElementInList_NoElementsRemaining() {
		list.add(one);
		Iterator<Integer> it = getIterator();
		it.next();
		it.remove();
		assertTrue(list.isEmpty());
	}


	@Test(expected = ConcurrentModificationException.class)
	public void next_OtherIteratorHasRemovedElement_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> first = getIterator();
		Iterator<Integer> second = getIterator();
		first.next();
		first.next();
		// Changes mod value
		first.remove();
		second.hasNext();
		second.next();
	}

	private void removeElementAtPositionWithIterator(int indexToRemove,
			Integer expectToBeRemoved) {
		Iterator<Integer> it = getIterator();
		int i = 0;
		for (; i < indexToRemove; i++) {
			assertTrue(it.hasNext());
			it.next();
		}
		assertTrue(it.hasNext());
		assertSame(expectToBeRemoved, it.next());
		it.remove();
		for (i = i + 1; i < allNumbers.length; i++) {
			assertTrue(it.hasNext());
			it.next();
		}
		assertFalse(it.hasNext());
	}

	private void addElementsToList() {
		for (Integer i : allNumbers) {
			assertTrue(list.add(i));
		}
	}

}
