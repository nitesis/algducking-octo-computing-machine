package ch.fhnw.algd1.converters.binary;

import java.util.*;
import java.math.*;

public class BinConverter {
	public static String toString(int x) {
		// TODO: expect x to be in range [-128, 127], return string with 8
		// binary digits representing x in 2-complement

		StringBuilder binaer = new StringBuilder();
		int counter = 0;

		// Wenn x negativ, wird es hier zunächst in positiven Wert umgewandelt
		if (x < 0) {
			x = 256 + x;
		}
		// Bis der String mit 8 Werten geüllt ist, wird...
		while (counter < 8) {
			// ...an die vorderste Stelle des String ein 0 oder 1 gesetzt, je
			// nach dem, ob Wert gerade oder ungerade ist
			binaer.insert(0, (x % 2));
			x = x / 2;
			counter++;

		}
		// binaer.reverse() entfällt hier, da oben binaer.insert(0) immer an der
		// vordersten Stelle des String den Wert setzt
		return binaer.toString();
	}

	public static int fromString(String text) {
		// TODO: expect text to contain 8 binary digits, parse to int value in
		// 2-complement
		int value = 0;
		if (text.charAt(0) == '1') {
			value = value - 128;
		}
		for (int i = 1; i < text.length(); i++) {
			if (text.charAt(i) == '1') {
				value = value + (int) (Math.pow(2, (7 - i)));
			}
		}

		return value;
	}

}