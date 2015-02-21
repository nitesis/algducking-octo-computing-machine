package ch.fhnw.algd2.arraycollections;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ch.fhnw.algd2.arraycollections.AbstractArrayCollection;
import ch.fhnw.algd2.arraycollections.SortedBag;
import ch.fhnw.algd2.arraycollections.general.AbstractBagTest;

public class SortedBagTest extends AbstractBagTest {

	@Override
	protected AbstractArrayCollection<Integer> createIntegerCollection(int size) {
		return new SortedBag<Integer>(size);
	}

	@Override
	protected Integer[] getExpectedOrderFor(Integer[] values) {
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(values));
		Collections.sort(list);
		Integer[] elements = new Integer[list.size()];
		int index = 0;
		for (Integer element : list) {
			elements[index++] = element;
		}
		return elements;
	}

	@Test(expected = ClassCastException.class)
	public void containsOtherObject() {
		Integer[] numbers = new Integer[] { 1, 2, 3 };
		addNumbersToBag(numbers);
		assertFalse(bag.contains("Asdf"));
	}
	
}
