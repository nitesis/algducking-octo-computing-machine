package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Checks simple iteration over empty and integer list.
 * 
 * @author Michael
 */
public abstract class Abstract_A_ListIterator_SimpleIteration extends AbstractListIteratorTest {

	@Test
	public void hasNext_OnEmptyList_False() {
		Iterator<Integer> it = getIterator();
		assertFalse(it.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void next_OnEmptyList_NoSuchElementException() {
		Iterator<Integer> it = getIterator();
		it.next();
	}
	
	@Test
	public void next_numbersOneToFiveAdded_checkIterationOrder() {
		int[] numbers = new int[] { 1, 2, 3, 4 };
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		Iterator<Integer> it = getIterator();
		for (int i = 0; i < numbers.length; i++) {
			assertTrue(it.hasNext());
			assertEquals(it.next().intValue(), numbers[i]);
		}
		assertFalse(it.hasNext());
	}
	
	@Test
	public void next_AfterFinishedIteration_NoSuchElementException(){
		int[] numbers = new int[] { 1, 2, 3, 4 };
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		Iterator<Integer> it = getIterator();
		for (int i=0; i< numbers.length; i++){
			it.next();
		}
		try {
			it.next();
			fail("Last call should throw an exception");
		} catch(NoSuchElementException ex){
			// pass
		}
	}

}
