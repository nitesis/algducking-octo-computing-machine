package ch.fhnw.algd1.converters.binary;

public class BinConverter {
	public static String toString(int x) {
		// TODO: expect x to be in range [-128, 127], return string with 8
		// binary digits representing x in 2-complement
		
		if (x < 0) {
			x = 256 + x;
		}

		
		convert(x);

	return Integer.toString(convert(x)).;
	}

	public static int fromString(String text) {
		// TODO: expect text to contain 8 binary digits, parse to int value in
		// 2-complement
		int result = 0;
		int i = text.length() - 1;
		while (i >= 0) {
			if (text.charAt(i) == '1') {
				result += (int) Math.pow(2, (text.length() - 1 - i));
			}
			i--;
		}
		return 0;
	}
	
	public static int convert(int n) {
	      if (n == 0){
	    	return 0;  
	      }
	      convert(n / 2);
	      System.out.print(n % 2);
	      return (n % 2);
	}
}