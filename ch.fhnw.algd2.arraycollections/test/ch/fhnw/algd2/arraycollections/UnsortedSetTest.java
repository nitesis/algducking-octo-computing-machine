package ch.fhnw.algd2.arraycollections;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ch.fhnw.algd2.arraycollections.AbstractArrayCollection;
import ch.fhnw.algd2.arraycollections.UnsortedSet;
import ch.fhnw.algd2.arraycollections.general.AbstractSetTest;

public class UnsortedSetTest extends AbstractSetTest {
	@Override
	protected AbstractArrayCollection<Integer> createIntegerCollection(int size) {
		return new UnsortedSet<Integer>(size);
	}

	@Override
	protected Integer[] getExpectedOrderFor(Integer[] values) {
		LinkedHashSet<Integer> set = new LinkedHashSet<Integer>(
				Arrays.asList(values));
		List<Integer> list = new LinkedList<Integer>(set);
		Integer[] elements = new Integer[list.size()];
		int index = 0;
		for (Integer element : list) {
			elements[index++] = element;
		}
		return elements;
	}

	@Test
	public void containsOtherObject() {
		Integer[] numbers = new Integer[] { 1, 2, 3 };
		addNumbersToBag(numbers);
		assertFalse(bag.contains("Asdf"));
	}
}
