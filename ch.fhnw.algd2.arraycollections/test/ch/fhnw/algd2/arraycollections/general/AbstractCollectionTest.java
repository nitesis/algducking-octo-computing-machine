package ch.fhnw.algd2.arraycollections.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.arraycollections.AbstractArrayCollection;

/**
 * Abstract Testclass containing test for all Bags / Sets and sorted / unsorted
 * 
 * @author Michael Henninger
 * 
 * @param <E>
 */
public abstract class AbstractCollectionTest {
	private static final int DEFAULT_SIZE = 10;

	protected AbstractArrayCollection<Integer> bag;

	@Before
	public void init() {
		bag = createIntegerCollection(DEFAULT_SIZE);
	}

	/*
	 * Default tests
	 */

	@Test
	public void testEmptyCollection() {
		assertTrue(bag.isEmpty());
		assertEquals(0, bag.size());
	}

	/*
	 * Test Adding
	 */
	@Test
	public void addNumberSequence() {
		Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5 };
		addNumbersToBag(numbers);
		checkElementsInBag(getExpectedOrderFor(numbers));
	}

	@Test
	public void addUnsortedNumberSequence() {
		Integer[] numbers = new Integer[] { 5, 4, 3, 2, 1 };
		addNumbersToBag(numbers);
		checkElementsInBag(getExpectedOrderFor(numbers));
	}

	@Test
	public void addDuplicates() {
		Integer[] numbers = new Integer[] { 1, 2, 3, 2, 5 };
		addNumbersToBag(numbers);
		checkElementsInBag(getExpectedOrderFor(numbers));
	}

	private void checkElementsInBag(Comparable<Integer>[] numbers) {
		Object[] bagContent = bag.toArray();
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(numbers[i], bagContent[i]);
		}
		assertEquals(numbers.length, bagContent.length);
	}

	@Test(expected = IllegalStateException.class)
	public void addMoreThanCapacity() {
		Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		try {
			addNumbersToBag(numbers);
		} catch (UnsupportedOperationException e) {
			fail("Should allow adding ten elements!");
		}
		bag.add(20);
		fail("Should only allow adding ten elements!");
	}

	@Test(expected = NullPointerException.class)
	public void addNull() {
		Integer[] numbers = new Integer[] { 1, null, 2, 3 };
		addNumbersToBag(numbers);
	}

	/*
	 * Check contains
	 */

	@Test(expected = NullPointerException.class)
	public void containsNull() {
		Integer[] numbers = new Integer[] { 1, 2, 3 };
		addNumbersToBag(numbers);
		assertTrue(bag.contains(null));
	}

	/*
	 * Check remove (and contains)
	 */

	@Test
	public void removeElement() {
		Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5 };
		addNumbersToBag(numbers);
		assertTrue(bag.contains(2));
		assertTrue(bag.contains(3));
		assertTrue(bag.remove(2));
		assertTrue(bag.remove(3));
		assertFalse(bag.contains(2));
		assertFalse(bag.contains(3));
		checkElementsInBag(getExpectedOrderFor(new Integer[] { 1, 4, 5 }));
	}

	@Test
	public void removeNonExisting() {
		Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5 };
		addNumbersToBag(numbers);
		assertFalse(bag.contains(22));
		assertFalse(bag.remove(22));
		assertEquals(numbers.length, bag.size());
	}

	@Test
	public void removeElementFromFullList() {
		Integer[] numbers = new Integer[10];
		for (int i = 0; i < 10; i++) {
			numbers[i] = i;
		}
		addNumbersToBag(numbers);
		assertTrue(bag.remove(5));
		assertEquals(numbers.length - 1, bag.size());
	}

	protected abstract AbstractArrayCollection<Integer> createIntegerCollection(
			int size);

	protected abstract Integer[] getExpectedOrderFor(Integer[] values);

	protected abstract void addNumbersToBag(Integer[] numbers);

}
