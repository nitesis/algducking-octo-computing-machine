package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Test;

public abstract class Abstract_B_ListIterator_CondurrentModification extends
		AbstractListIteratorTest {

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.add(5);
		assertTrue(it.hasNext());
		it.next();
		fail("Receiving element should throw an ConcurrentModificationException");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementReomvedAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.remove(new Integer(2));
		assertTrue(it.hasNext());
		it.next();
		fail("Receiving element should throw an ConcurrentModificationException");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementRemovedByIndexAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.remove(1);
		assertTrue(it.hasNext());
		it.next();
		fail("Receiving element should throw an ConcurrentModificationException");
	}


	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedAtHeadAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.add(0, new Integer(3));
		it.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedAtTailAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.add(list.size(), new Integer(3));
		it.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedInBetweenAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.add(2, new Integer(3));
		it.next();
	}
	
	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementRemovedAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = getIterator();
		list.remove(new Integer(3));
		it.next();
	}
	
}
