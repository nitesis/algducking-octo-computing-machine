package ch.fhnw.algd1.stringmatch;

import ch.fhnw.algd1.stringmatch.framework.IStringMatch;

public class NaiveStringMatch1 implements IStringMatch {
	@Override
	public int firstMatch(String text, String pattern) {
		// TODO: sequential search for first i such that matches(text, i,
		// pattern)
		int i = 0;
		while (i <= text.length() - pattern.length()) {
			if (matches(text, i, pattern)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private boolean matches(String text, int start, String pattern) {
		// TODO: return if pattern matches text from postion start
		int j = 0;
//		while ((start + j) < text.length()) {
			while (j < pattern.length()
					&& text.charAt(start + j) == pattern.charAt(j)) {
				j++;
			}
//			start++;
//		}
		return (j == pattern.length());

	}
}