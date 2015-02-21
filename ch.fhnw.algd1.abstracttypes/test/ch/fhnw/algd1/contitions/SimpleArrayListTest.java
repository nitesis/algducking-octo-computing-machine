package ch.fhnw.algd1.contitions;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd1.conditions.SimpleArrayList;

public class SimpleArrayListTest {

	private SimpleArrayList<Integer> list;

	@Before
	public void setUp() {
		list = new SimpleArrayList<>();
	}

	@Test
	public void testGetOnValidIndex() {
		list.add(1);
		list.add(2);
		assertEquals(1, list.get(0).intValue());
		assertEquals(2, list.get(1).intValue());
		assertEquals(2, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOnNegativIndex_ExpectException() {
		list.add(1);
		list.get(-4);
		fail("Exception was expected");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOnTooLargeIndex_ExpectException() {
		list.add(1);
		list.get(4);
		fail("Exception was expected");
	}

	@Test
	public void testIndexAdd_WithoutEnlarge() {
		int[] numbers = new int[] { 1, 4, 6, 7 };
		for (int i = numbers.length - 1; i >= 0; i--) {
			list.add(0, numbers[i]);
		}
		assertListValues(numbers);
	}

	@Test
	public void testIndexSet() {
		int[] numbers = new int[] { 1, 4, 6, 7 };
		int last = numbers[numbers.length - 1];
		list.add(numbers[numbers.length - 1]);
		for (int i = numbers.length - 2; i >= 0; i--) {
			assertEquals(last, list.set(0, numbers[i]).intValue());
			last = numbers[i];
		}
		assertEquals(numbers[0], list.get(0).intValue());
		assertEquals(1, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetOnNegativIndex_ExpectException() {
		list.add(1);
		list.set(-4, 2);
		fail("Exception was expected");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetOnTooLargeIndex_ExpectException() {
		list.add(1);
		list.set(4, 2);
		fail("Exception was expected");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddOnNegativIndex_ExpectException() {
		list.add(-1, 2);
		fail("Exception was expected");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddOnTooLargeIndex_ExpectException() {
		list.add(44444, 2);
		fail("Exception was expected");
	}

	@Test
	public void testAddWithEnlarge() {
		int[] numbers = new int[1000];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
			list.add(i, i);
		}
		assertListValues(numbers);
	}

	@Test
	public void testRemove() {
		int[] numbers = new int[10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
			list.add(i, i);
		}
		assertListValues(numbers);
		list.remove(1);
		list.remove(0);
		int[] remainingNumbers = Arrays.copyOfRange(numbers, 2, numbers.length);
		assertListValues(remainingNumbers);
	}

	@Test
	public void addNullValue() {
		list.add(null);
	}

	private void assertListValues(int[] numbers) {
		assertEquals(numbers.length, list.size());
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(numbers[i], list.get(i).intValue());
		}
	}
}
