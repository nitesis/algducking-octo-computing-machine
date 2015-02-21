package ch.fhnw.algd1.stringmatch;

import ch.fhnw.algd1.stringmatch.framework.IStringMatch;

public class NaiveStringMatch2Test extends AbstractStringMatchTest {

	@Override
	protected IStringMatch getMatcher() {
		return new NaiveStringMatch2();
	}

}
