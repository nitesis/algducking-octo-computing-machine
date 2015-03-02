package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.junit.Test;

import ch.fhnw.algd2.collections.list.DoublyLinkedListFactory;
import ch.fhnw.algd2.collections.list.MyAbstractList;

/**
 * Tests list-iterator specific functions
 */
public class D_DLL_ListIteratorTest extends AbstractListIteratorTest {

	private Integer one = Integer.valueOf(1);
	private Integer two = Integer.valueOf(2);
	private Integer three = Integer.valueOf(3);
	private Integer four = Integer.valueOf(4);
	private Integer five = Integer.valueOf(5);

	private Integer[] allNumbers = new Integer[] { one, two, three, four, five };

	@Override
	protected MyAbstractList<Integer> createInstance() {
		return DoublyLinkedListFactory.createInstance();
	}

	@Test
	public void testForwardAndBackwardIteration() {
		addAllNumberToList(allNumbers);
		checkIteratorForwardsAndBackwards(allNumbers);
	}

	@Test
	public void testIndexCounter() {
		addNumbersFromOneToFiveToList();
		ListIterator<Integer> it = list.listIterator();
		for (int i = 0; i < 5; i++) {
			assertTrue(it.hasNext());
			assertEquals(i, it.nextIndex());
			it.next();
		}

		for (int i = 4; i >= 0; i--) {
			assertTrue(it.hasPrevious());
			assertEquals(i, it.previousIndex());
			it.previous();
		}
	}

	@Test
	public void testAddingByIndex_AtEnd() {
		Integer six = Integer.valueOf(6);
		addAllNumberToList(allNumbers);
		list.add(list.size(), six);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, three,
				four, five, six });
	}

	@Test
	public void testAddingByIndex_AtStart() {
		Integer six = Integer.valueOf(6);
		addAllNumberToList(allNumbers);
		list.add(0, six);
		checkIteratorForwardsAndBackwards(new Integer[] { six, one, two, three,
				four, five });
	}

	@Test
	public void testAddingByIndex_InBetween() {
		Integer six = Integer.valueOf(6);
		addAllNumberToList(allNumbers);
		list.add(0, six);
		checkIteratorForwardsAndBackwards(new Integer[] { six, one, two, three,
				four, five });
	}

	@Test
	public void testRemoveByIndex_AtStart() {
		addAllNumberToList(allNumbers);
		list.remove(0);
		checkIteratorForwardsAndBackwards(new Integer[] { two, three, four,
				five });
	}

	@Test
	public void testRemoveByIndex_AtEnd() {
		addAllNumberToList(allNumbers);
		list.remove(list.size() - 1);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, three, four });
	}

	@Test
	public void testRemoveByIndex_InBetween() {
		addAllNumberToList(allNumbers);
		list.remove(2);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, four, five });
	}

	@Test
	public void testRemoveByElement_AtStart() {
		addAllNumberToList(allNumbers);
		list.remove(one);
		checkIteratorForwardsAndBackwards(new Integer[] { two, three, four,
				five });
	}

	@Test
	public void testRemoveByElement_AtEnd() {
		addAllNumberToList(allNumbers);
		list.remove(five);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, three, four });
	}

	@Test
	public void testRemoveByElement_InBetween() {
		addAllNumberToList(allNumbers);
		list.remove(two);
		checkIteratorForwardsAndBackwards(new Integer[] { one, three, four,
				five });
	}

	@Test
	public void testRemoveAfterPrevious() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		assertSame(one, it.next());
		assertSame(two, it.next());
		assertSame(three, it.next());
		assertSame(three, it.previous());
		it.remove(); // removes three
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, four, five });
	}

	@Test(expected = IllegalStateException.class)
	public void testSetWithoutNext() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		it.set(Integer.valueOf(3));
	}

	@Test
	public void testSetAtHead() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		Integer zero = Integer.valueOf(0);
		it.next();
		it.set(zero);
		checkIteratorForwardsAndBackwards(new Integer[] { zero, two, three,
				four, five });
		assertSame(zero, it.previous());
	}

	@Test
	public void testSetAtTail() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		Integer zero = Integer.valueOf(0);
		while (it.hasNext()) {
			it.next();
		}
		it.set(zero);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, three,
				four, zero });
	}

	@Test
	public void testSetInBetween() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		Integer zero = Integer.valueOf(0);
		it.next();
		it.next();
		it.set(zero);
		checkIteratorForwardsAndBackwards(new Integer[] { one, zero, three,
				four, five });
	}

	@Test
	public void testAddAtHead() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		Integer zero = Integer.valueOf(0);
		assertEquals(0, it.nextIndex());
		it.add(zero);
		checkIteratorForwardsAndBackwards(new Integer[] { zero, one, two,
				three, four, five });
		assertEquals(1, it.nextIndex());
		assertEquals(one, it.next());
	}

	@Test
	public void testAddAtEnd() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		while (it.hasNext()) {
			it.next();
		}
		Integer zero = Integer.valueOf(0);
		assertEquals(list.size(), it.nextIndex());
		it.add(zero);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, three,
				four, five, zero });
		assertEquals(list.size(), it.nextIndex());
		assertEquals(zero, it.previous());
	}

	@Test
	public void testAddInBetween() {
		addAllNumberToList(allNumbers);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.next();
		Integer zero = Integer.valueOf(0);
		assertEquals(2, it.nextIndex());
		it.add(zero);
		checkIteratorForwardsAndBackwards(new Integer[] { one, two, zero,
				three, four, five });
		assertEquals(3, it.nextIndex());
		assertEquals(zero, it.previous());
	}

	private void checkIteratorForwardsAndBackwards(Integer[] numbers) {
		// Forward Iteration
		ListIterator<Integer> it = list.listIterator();
		for (int i = 0; i < numbers.length; i++) {
			assertTrue(it.hasNext());
			assertSame(numbers[i], it.next());
		}
		assertFalse(it.hasNext());
		for (int i = numbers.length - 1; i >= 0; i--) {
			assertTrue(it.hasPrevious());
			assertSame(numbers[i], it.previous());
		}
		assertFalse(it.hasPrevious());
	}

	private void addAllNumberToList(Integer[] allNumbers) {
		for (Integer i : allNumbers) {
			assertTrue(list.add(i));
		}
	}

}
