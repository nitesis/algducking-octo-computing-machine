package ch.fhnw.algd.apprentice;

public class Fibonacci {

	public static void main(String[] args) {
		int ergebnis = fibonacciRecursive(4);
		System.out.println("Das Ergebnis lautet: " + ergebnis);
	}

	private static int fibonacciRecursive(int i) {
		if (i <= 0) {
			return 0;
		}
		else if (i == 1) {
			return 1;
		} else {
			//System.out.println("i ist jetzt: " + i);
			return fibonacciRecursive(i - 1) + fibonacciRecursive(i - 2);
		}
		
	}

}
