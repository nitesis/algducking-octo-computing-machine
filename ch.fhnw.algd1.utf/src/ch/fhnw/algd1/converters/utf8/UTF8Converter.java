package ch.fhnw.algd1.converters.utf8;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public class UTF8Converter {
	public static byte[] codePointToUTF(int x) {
		byte[] b = null;
		// TODO allocate b in the right size (depending on x) and fill it with the
		// UTF-8 encoding of code point x. b[0] shall contain the first byte.
		return b;
	}

	public static int UTFtoCodePoint(byte[] bytes) {
		if (isValidUTF8(bytes)) {
			// TODO replace return statement below by code to return the code point
			// UTF-8 encoded in array bytes. bytes[0] contains the first byte
			return 0;
		} else return 0;
	}

	private static boolean isValidUTF8(byte[] bytes) {
		if (bytes.length == 1) return (bytes[0] & 0b1000_0000) == 0;
		else if (bytes.length == 2) return ((bytes[0] & 0b1110_0000) == 0b1100_0000)
				&& isFollowup(bytes[1]);
		else if (bytes.length == 3) return ((bytes[0] & 0b1111_0000) == 0b1110_0000)
				&& isFollowup(bytes[1]) && isFollowup(bytes[2]);
		else if (bytes.length == 4) return ((bytes[0] & 0b1111_1000) == 0b1111_0000)
				&& isFollowup(bytes[1]) && isFollowup(bytes[2]) && isFollowup(bytes[3]);
		else return false;
	}

	private static boolean isFollowup(byte b) {
		return (b & 0b1100_0000) == 0b1000_0000;
	}
}
