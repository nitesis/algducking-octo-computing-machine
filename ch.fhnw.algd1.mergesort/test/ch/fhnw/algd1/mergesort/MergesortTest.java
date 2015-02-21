package ch.fhnw.algd1.mergesort;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MergesortTest {

	private Mergesort sort;

	@Before
	public void init() {
		sort = new Mergesort();
	}

	@Test
	public void testEmptyArray() {
		int[] numbers = new int[] {};
		sortAndCheck(transform(numbers));
	}

	private Integer[] transform(int[] numbers) {
		Integer[] arr = new Integer[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			arr[i] = new Integer(numbers[i]);
		}
		return arr;
	}

	@Test
	public void testArrayWithOneElement() {
		int[] numbers = new int[] { 2 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testSameNumbers() {
		int[] numbers = new int[] { 2, 2, 2, 2, 2 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testAlreadySorted() {
		int[] numbers = new int[] { 1, 2, 3, 4, 5 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testRandomOrder() {
		int[] numbers = new int[] { 34, 64, 12, 6, 8, 345, 54 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testDescendingOrder() {
		int[] numbers = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testStrings() {
		String[] strings = new String[] { "z", "b", "x", "z", "b", "x", "a" };
		sortAndCheck(strings);
	}

	@Test
	public void testStability() {
		String one = new String("Z"), two = new String("Z"), three = new String(
				"Z"), four = new String("Z"), five = new String("Z"), six = new String(
				"Z"), seven = new String("Z"), eight = new String("Z"), nine = new String(
				"Z"), ten = new String("Z");
		String[] original = new String[] { one, two, three, four, five, six,
				seven, eight, nine, ten };

		String[] strings = new String[] { one, two, three, four, five, six,
				seven, eight, nine, ten };
		sortAndCheck(strings);

		for (int i = 0; i < original.length; i++) {
			assertTrue(strings[i] == original[i]);
		}
	}

	private <T extends Comparable<T>> void sortAndCheck(T[] numbers) {
		int nBefore = numbers.length;
		sort.mergesort(numbers);
		assertEquals(nBefore, numbers.length);
		assertTrue(isAscending(numbers));
	}

	private <T extends Comparable<T>> boolean isAscending(T[] a) {
		if (a.length == 0)
			return true;

		int i = 1;
		while (i < a.length && a[i - 1].compareTo(a[i]) <= 0) {
			i++;
		}
		return i == a.length;
	}

}
