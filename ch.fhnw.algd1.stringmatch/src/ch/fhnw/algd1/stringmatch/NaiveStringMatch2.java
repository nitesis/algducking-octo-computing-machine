package ch.fhnw.algd1.stringmatch;

import ch.fhnw.algd1.stringmatch.framework.IStringMatch;

public class NaiveStringMatch2 implements IStringMatch {
	@Override
	public int firstMatch(String text, String pattern) {
		// TODO: search for first start position i where pattern matches text
		int i = 0;
		int j = 0;
		
		while (i + pattern.length() <= text.length() && j < pattern.length()) {
			j = 0;
			while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) {
				j++;
				
			}
			if (j < pattern.length()){
				i++;
			}
			
		}
		return i + pattern.length() > text.length() ? -1 : i;
	}
}