package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.fhnw.algd2.collections.list.linkedlist.general.AbstractMyLinkedListTest;

/**
 * - Supporting null
 */
public class E_MyLinkedListTest_SupportingNull extends AbstractMyLinkedListTest {

	@Test
	public void add_Null_NullAdded() {
		Integer i = null;
		list.add(i);
		assertEquals(1, list.size());
		assertNull(list.toArray()[0]);
	}

	@Test
	public void contains_NullWhenNullAdded_True() {
		addNumbersFromOneToFiveToList();
		list.add(null);
		assertTrue(list.contains(null));
	}

	@Test
	public void checkAddingAndRemovingNull_ListWithNumbers1To5_NullAddedAndRemoved() {
		addNumbersFromOneToFiveToList();
		list.add(null);
		assertNull(list.toArray()[5]);
		list.remove(null);
		assertFalse(list.contains(null));
	}

	@Test
	public void remove_NullIfNotExists_False() {
		assertFalse(list.remove(null));
	}

	@Test
	public void contains2_NullValueInList_False() {
		list.add(null);
		assertFalse(list.contains(2));
	}

	@Test
	public void removeTwo_NullValueInList_False() {
		list.add(null);
		assertFalse(list.remove("Zwei"));
	}
	
    @Test
    public void removeTwo_NullValueInListNotHead_False() {
          list.add(2);
          list.add(null);
          assertFalse(list.remove("Zwei"));
    }

    
    @Test

    public void contains_elementAfterNull_true() {
          list.add(1);
          list.add(null);
          list.add(2);
          assertTrue(list.contains(2));

    }
    
}
