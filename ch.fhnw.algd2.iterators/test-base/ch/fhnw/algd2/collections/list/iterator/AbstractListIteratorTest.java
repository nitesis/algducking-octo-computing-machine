package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public abstract class AbstractListIteratorTest {

	protected MyAbstractList<Integer> list;

	@Before
	public void init() {
		list = createInstance();
	}

	protected abstract MyAbstractList<Integer> createInstance();

	protected final Iterator<Integer> getIterator(){
		return list.iterator();
	}

	protected void addNumbersFromOneToFiveToList() {
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5 };
		for (Integer i : ints) {
			assertTrue(list.add(i));
		}
	}

}
