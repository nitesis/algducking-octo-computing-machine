package ch.fhnw.algd1.stringmatch;

import ch.fhnw.algd1.stringmatch.framework.IStringMatch;

/**
 * Boyer Moore String Match (supporting LATIN-1 character set)
 */
public class BoyerMooreStringMatch implements IStringMatch {
	private int[] lastPositions(String pattern) {
		int[] p = new int[256]; // we support the LATIN-1 character set
		// TODO preprocess pattern, create occurrence heuristics
		return p;
	}

	public int firstMatch(String text, String pattern) {
		int[] lastPos = lastPositions(pattern);
		// TODO: search for first start position i where pattern matches text
		return 0;
	}
}
