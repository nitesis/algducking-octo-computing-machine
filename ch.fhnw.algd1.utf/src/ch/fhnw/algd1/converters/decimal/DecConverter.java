package ch.fhnw.algd1.converters.decimal;
/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public class DecConverter {
	public static int fromString(String text) {
		return Integer.parseInt(text);
	}

	public static String toString(int x) {
		return Integer.toString(x);
	}
}
