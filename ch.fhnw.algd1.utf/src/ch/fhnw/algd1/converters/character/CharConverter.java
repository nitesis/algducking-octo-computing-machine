package ch.fhnw.algd1.converters.character;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public class CharConverter {
	public static String toString(int x) {
		StringBuilder s = new StringBuilder();
		s.appendCodePoint(x);
		return s.toString();
	}

	public static int fromString(String text) {
		return text.codePointAt(0);
	}
}
