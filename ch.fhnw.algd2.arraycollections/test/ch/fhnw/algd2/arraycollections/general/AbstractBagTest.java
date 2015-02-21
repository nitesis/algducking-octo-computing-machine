package ch.fhnw.algd2.arraycollections.general;

import static org.junit.Assert.assertTrue;

public abstract class AbstractBagTest extends AbstractCollectionTest {

	@Override
	protected void addNumbersToBag(Integer[] numbers) {
		for (Integer number : numbers) {
			assertTrue(bag.add(number));
		}
	}

}
