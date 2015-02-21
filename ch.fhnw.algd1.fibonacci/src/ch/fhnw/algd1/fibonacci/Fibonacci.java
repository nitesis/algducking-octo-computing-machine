package ch.fhnw.algd1.fibonacci;

/*
 * Created on 25.11.2013
 */
/**
 * @author
 */
public class Fibonacci {
	public static void main(String[] args) {
		// index of Fibonacci number to be computed
		int n = 30;
		// measure fiboIter
		long t = System.currentTimeMillis();
		long f = fiboIter(n);
		t = System.currentTimeMillis() - t;
		System.out.println("fiboIter(" + n + ") = " + f + " - computation took "
				+ t + " msec.");
		// measure fiboRek
		t = System.currentTimeMillis();
		f = fiboRec(n);
		t = System.currentTimeMillis() - t;
		System.out.println("fiboRek(" + n + ") = " + f + " - computation took " + t
				+ " msec.");
	}

	public static long fiboRec(int n) {
		// TODO: rekursive Berechnung der Fibonacci Zahl f(n)
		if (n <= 1) {
			return n;
		}
		else return 
			fiboRec(n - 1) + fiboRec(n - 2);
	}

	public static long fiboIter(int n) {
		if (n < 1) return n;
		else {
			int i = 1;
			long f = 0, f1 = 0; // TODO: f, f1 korrekt initialisieren
			while (i < n) { // Invariante: f = f(i) AND f1 = f(i-1)
				// TODO: Vorbedingung dafür etablieren, dass Invariante nach i=i+1 gilt
				f = f(i + 1) && f1 = f(i);
				i = i + 1;
			}
			return f;
		}
	}
}