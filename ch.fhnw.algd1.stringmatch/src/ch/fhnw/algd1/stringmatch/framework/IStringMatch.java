package ch.fhnw.algd1.stringmatch.framework;

public interface IStringMatch {
	/**
	 * Returns position of first match or -1 if no match
	 * 
	 * @param pattern
	 * @param text
	 * @return Match-Position or -1 in case of no match
	 */
	int firstMatch(String text, String pattern);
}
